package umc.spring.service.OAuthService;

import jakarta.servlet.http.HttpServletResponse;
import umc.spring.web.dto.user.UserResponse;

public interface OAuthService {
    // 카카오 소셜 로그인
    UserResponse.LoginResultDto kakaoOAuthLogin(String accessCode, HttpServletResponse response);
}
