package umc.spring.config.security.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.config.properties.Constants;
import umc.spring.config.properties.JwtProperties;

import java.security.Key;
import java.util.Date;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    // 인증 정보를 받아 JWT Access Token을 생성하고 반환
    public String generateToken(Authentication authentication) {
        String email = authentication.getName();

        return Jwts.builder()
                .setSubject(email) // 이메일을 subject에 저장
                .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰 유효성 검증
    // 토큰 만료, 위조, 형식 오류 등일 때 예외 발생
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            throw ex; // 외부에서 처리하게 던짐
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 토큰에서 인증 정보를 추출하여 Spring Security의 Authentication 객체로 변환
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        // 이메일을 사용하여 인증 객체 생성
        // User 객체의 username 필드에 이메일이 들어가므로, 이후 인증 객체에서 getName() 호출 시 이메일이 반환
        User principal = new User(email, "", Collections.singleton(() -> role));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    // HTTP 요청의 헤더에서 JWT 토큰을 추출
    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }

    // HttpServletRequest 에서 토큰 값을 추출 후
    // getAuthentication 메소드를 이용해서 Spring Security의 Authentication 객체로 변환
    public Authentication extractAuthentication(HttpServletRequest request){
        String accessToken = resolveToken(request);
        if(accessToken == null || !validateToken(accessToken)) {
            throw new ErrorHandler(ErrorStatus.INVALID_TOKEN);
        }
        return getAuthentication(accessToken);
    }
}
