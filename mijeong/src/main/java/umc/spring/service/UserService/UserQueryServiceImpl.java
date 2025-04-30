package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}
