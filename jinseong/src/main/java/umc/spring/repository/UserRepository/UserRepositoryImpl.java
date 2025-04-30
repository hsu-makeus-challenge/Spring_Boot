package umc.spring.repository.UserRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QUser;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    private final QUser user = QUser.user;

    @Override
    public Tuple dynamicQueryWithBooleanBuilder(Long userId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(user.id.eq(userId));

        return jpaQueryFactory
                .select(
                        user.nickname,
                        user.email,
                        user.phoneNumber,
                        user.verified,
                        user.point
                )
                .from(user)
                .where(predicate)
                .fetchOne();
    }
}
