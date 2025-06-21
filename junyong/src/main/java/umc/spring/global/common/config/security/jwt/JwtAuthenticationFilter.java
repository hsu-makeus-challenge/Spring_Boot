package umc.spring.global.common.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.spring.global.common.apiPayload.ApiResponse;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthenticationFilter");
        String uri = request.getRequestURI();

        log.info(" permit 검사");
        // permitAll 경로는 토큰 검사하지 않음
        if (uri.startsWith("/login") || uri.startsWith("/signup") || uri.startsWith("/members/join") ||
                uri.startsWith("/members/login") || uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs") ||
                uri.equals("/") || uri.equals("/home") ||
                uri.startsWith("/oauth2/") || uri.startsWith("/login/oauth2/") || uri.startsWith("/api/token")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        log.info("토큰 있는지 검사");
        if (!StringUtils.hasText(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            objectMapper.writeValue(response.getOutputStream(),
                    ApiResponse.onFailure(ErrorStatus.INVALID_TOKEN.getCode(), ErrorStatus.INVALID_TOKEN.getMessage(), null));
            return; // 필터 체인 진행 중단
        }

        ErrorStatus errorCode = null;

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                errorCode = ErrorStatus.INVALID_TOKEN;
            }
        } catch (ExpiredJwtException e) {
            errorCode = ErrorStatus.TOKEN_EXPIRED;
        } catch (Exception e) {
            errorCode = ErrorStatus.FAIL_AUTHENTICATION;
        }

        if (errorCode != null) {
            request.setAttribute("exception", errorCode);
        }


        filterChain.doFilter(request, response);


//        ErrorStatus errorCode = jwtTokenProvider.validateToken(token) ? null : ErrorStatus.INVALID_TOKEN;
//        log.info("errorCode = {}", errorCode);
//
//        if (errorCode == null) {
//            try {
//                Authentication authentication = jwtTokenProvider.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (Exception e) {
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                objectMapper.writeValue(
//                        response.getOutputStream(),
//                        ApiResponse.onFailure(ErrorStatus.FAIL_AUTHENTICATION.getCode(), ErrorStatus.FAIL_AUTHENTICATION.getMessage(), null)
//                );
//                return;
//            }
//        } else {
//            request.setAttribute("exception", errorCode);
//        }
//
//        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }
}
