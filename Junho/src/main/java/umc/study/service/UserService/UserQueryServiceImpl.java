package umc.study.service.UserService;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.QPhoneVerification;
import umc.study.domain.QUser;
import umc.study.domain.User;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.HomeInfoDto;
import umc.study.web.dto.MyInfoDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public MyInfoDto getMyInfo(Long userId) {
        return userRepository.getMyInfo(userId);
    }

    @Override
    public HomeInfoDto getHomeInfo(Long userId) {
        return userRepository.getHomeInfo(userId);
    }
}
