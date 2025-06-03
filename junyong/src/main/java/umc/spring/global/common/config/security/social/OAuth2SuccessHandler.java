package umc.spring.global.common.config.security.social;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import umc.spring.domain.token.data.RefreshToken;
import umc.spring.domain.token.repository.RefreshTokenRepository;
import umc.spring.global.common.config.security.jwt.JwtTokenProvider;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("handler");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("attributes = {}", oAuth2User.getAttributes());

        String email = oAuth2User.getAttribute("email");
        log.info("email = {}", email);

        String accessToken = jwtTokenProvider.generateToken(email, "USER");
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);
        log.info("accessToken = {}", accessToken);
        log.info("refreshToken = {}", refreshToken);

        addRefresh(email, refreshToken);

//        String targetUrl = UriComponentsBuilder.fromUriString("/home")
//                .queryParam("token", accessToken)
//                .build().toUriString();
//
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);

        // JWT 토큰을 헤더에 추가
        response.setHeader("Authorization", "Bearer " + accessToken);

        // 리디렉션
        response.sendRedirect("/home?token=" + accessToken);

    }

    protected void addRefresh(String email, String refresh) {
        RefreshToken refreshToken = refreshTokenRepository.findByEmail(email)
                .map(token -> token.updateToken(refresh))
                .orElse(
                        RefreshToken.builder().
                        email(email).
                        refreshToken(refresh).
                        build()
                );
        refreshTokenRepository.save(refreshToken);
    }

}
