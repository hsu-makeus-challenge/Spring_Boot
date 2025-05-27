package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.user.UserResponse;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponse.MyPageDto getUserMyPage(Long userId) {
        UserResponse.MyPageDto user = userRepository.getUserMyPage(userId);
        return user;
    }

    // User 존재 여부 검증
    @Override
    public Boolean existsUserById(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    // 유저 반환
    @Override
    public User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));
    }

    // 유저 정보 조회


    @Override
    public UserResponse.UserInfoDto getUserInfo(HttpServletRequest request) {
        // JwtTokenProvider에 정의한 extractAuthentication 메소드를 통해 토큰을 파싱하고, Authentication 객체를 추출
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);

        // 추출해낸 인증 객체를 통해 사용자 정보(이메일) 추출
        // 토큰 생성 시 토큰의 subject 필드에 사용자 이메일을 저장했기 때문
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));
        return UserConverter.toUserInfoDto(user);
    }
}
