package umc.spring.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.config.properties.Constants;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.config.security.jwt.RefreshToken;
import umc.spring.repository.RefreshTokenRepository.RefreshTokenRepository;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // Access & RefreshToken 재발급
    @Override
    public void reissueTokens(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = JwtTokenProvider.resolveToken(request);  // AccessToken 추출
        String refreshToken = request.getHeader("Refresh-Token");     // RefreshToken은 Bearer 없이 저장한다고 가정

        if (accessToken == null || refreshToken == null) {
            throw new ErrorHandler(ErrorStatus.INVALID_TOKEN);
        }

        // refresh token 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new ErrorHandler(ErrorStatus.INVALID_TOKEN);
        }

        // refresh token에서 사용자 email 추출
        String email = jwtTokenProvider.getAuthentication(refreshToken).getName();

        // 저장된 refresh token과 비교
        RefreshToken savedToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.INVALID_TOKEN));

        if (!savedToken.getToken().equals(refreshToken)) {
            throw new ErrorHandler(ErrorStatus.INVALID_TOKEN);
        }

        // 재발급
        Map<String, String> tokens = jwtTokenProvider.regenerateAccessTokenAndRotateRefreshToken(email);

        // 응답 헤더 설정
        Constants.setAllTokens(response, accessToken, refreshToken);
    }
}
