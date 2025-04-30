package umc.spring.repository.UserRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QUser;
import umc.spring.domain.User;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public User findUserInfoByUserNo(Long userNo) {
        QUser user = QUser.user;

        return queryFactory
                .selectFrom(user)
                .where(user.id.eq(userNo))
                .fetchOne();
    }
}
