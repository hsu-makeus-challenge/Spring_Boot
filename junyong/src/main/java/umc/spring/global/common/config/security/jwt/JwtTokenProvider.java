package umc.spring.global.common.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    // Jwt Token 생성 및 반환
    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        return Jwts.builder()
                .setSubject(email)
                .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        return Jwts.builder().setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("validateToken() 내부 e = {}", e.getMessage());
            return false;
        }
    }

    // 토큰 유효기간 검사
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    // JWT 토큰에서 인증 정보 추출 -> Authentication 객체 반환
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        User principal = new User(email, "", Collections.singleton(() -> role));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    // 순수 토큰 반환
    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }

    public Authentication extractAuthentication(HttpServletRequest request) {
        String accessToken = resolveToken(request);
        if(accessToken == null || !validateToken(accessToken)) {
            throw new ErrorHandler(ErrorStatus.INVALID_TOKEN);
        }
        return getAuthentication(accessToken);
    }



}
