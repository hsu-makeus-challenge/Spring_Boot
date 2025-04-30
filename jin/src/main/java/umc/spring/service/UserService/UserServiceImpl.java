package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserInfo(Long userNo) {
        return userRepository.findUserInfoByUserNo(userNo);
    }
}
