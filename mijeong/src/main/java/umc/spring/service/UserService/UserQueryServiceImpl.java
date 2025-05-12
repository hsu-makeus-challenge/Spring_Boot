package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.user.UserResponse;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public UserResponse.MyPageDto getUserMyPage(Long userId) {
        UserResponse.MyPageDto user = userRepository.getUserMyPage(userId);
        return user;
    }

    // User 존재 여부 검증
    @Override
    public Boolean existsUserById(Long userId) { return userRepository.existsById(userId); }

    // 유저 반환
    @Override
    public User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));
    }
}
