package umc.spring.service.OAuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.config.security.OAuth2.KakaoUtil;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.OAuthConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.OAuth;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.SocialType;
import umc.spring.repository.OAuthRepository.OAuthRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.OAuth2.KakaoDto;
import umc.spring.web.dto.user.UserResponse;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OAuthServiceImpl implements OAuthService {

    private final KakaoUtil kakaoUtil;
    private final UserRepository userRepository;
    private final OAuthRepository oauthRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 카카오 소셜 로그인
    @Transactional
    @Override
    public UserResponse.LoginResultDto kakaoOAuthLogin(String accessCode, HttpServletResponse response) {
        // 1. 카카오로부터 액세스 토큰 발급
        KakaoDto.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        if (oAuthToken == null) throw new RuntimeException("카카오 액세스 토큰 요청 실패");

        // 2. 액세스 토큰으로 사용자 정보 조회
        KakaoDto.KakaoProfile kakaoProfile = kakaoUtil.requestUserInfo(oAuthToken.getAccess_token());
        if (kakaoProfile == null) throw new RuntimeException("카카오 사용자 정보 요청 실패");

        String email = kakaoProfile.getKakao_account().getEmail();
        String nickname = kakaoProfile.getKakao_account().getProfile().getNickname();

        // 3. 사용자 존재 여부 확인 → 없으면 자동 회원가입
        AtomicBoolean isNewUser = new AtomicBoolean(false); // 람다 내부에서 값 변경 가능

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            isNewUser.set(true);  // 새 사용자임을 표시

            // 더미 데이터로 사용자 생성
            User newUser = User.builder()
                    .email(email)
                    .nickName(nickname)
                    .role(Role.USER)
                    .gender(Gender.NONE)
                    .birth("2004/01/08")
                    .address("test address")
                    .phone("010-1111-1111")
                    .build();

            return userRepository.save(newUser);
        });

        // 4. 소셜로그인 정보 저장
        // 새로 가입한 사용자일 때만 소셜 로그인 정보 저장
        if (isNewUser.get()) {
            OAuth oAuth = OAuthConverter.toOAuth(user, SocialType.KAKAO);
            oauthRepository.save(oAuth);
        }

        // 5. JWT 토큰 생성 및 헤더에 설정
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);
        response.setHeader("Authorization", "Bearer " + accessToken);

        // 6. 로그인 성공 DTO 반환
        log.info("로그인 완료, userId: {}, Access Token: {}", user.getId(), accessToken);
        return UserConverter.toLoginResultDto(
                user.getId()
        );
    }
}
