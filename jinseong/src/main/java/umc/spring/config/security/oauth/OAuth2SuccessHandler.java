package umc.spring.config.security.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import umc.spring.config.security.jwt.JwtTokenProvider;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String jwt = jwtTokenProvider.generateToken(authentication);
        String name = ((DefaultOAuth2User) authentication.getPrincipal()).getAttribute("name");

        // 프론트로 JWT 전달 (리디렉션 or 헤더 전달 방식 가능)
        String redirectUrl = "/oauth?token=" + jwt + "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        response.sendRedirect(redirectUrl);
    }
}
