package umc.spring.service.UserService;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.QUser;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Tuple findUserInfoById(Long userId) {

        Tuple userInfo = userRepository.dynamicQueryWithBooleanBuilder(userId);

        System.out.println("nickname: " + userInfo.get(QUser.user.nickname));
        System.out.println("email: " + userInfo.get(QUser.user.email));
        System.out.println("phoneNumber: " + userInfo.get(QUser.user.phoneNumber));
        System.out.println("verified: " + userInfo.get(QUser.user.verified));
        System.out.println("point: " + userInfo.get(QUser.user.point));

        return userInfo;
    }
}
