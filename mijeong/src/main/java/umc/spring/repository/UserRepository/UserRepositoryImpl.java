package umc.spring.repository.UserRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QUser;
import umc.spring.domain.QUserPoint;
import umc.spring.web.dto.user.UserResponse;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    private final QUser user = QUser.user;
    private final QUserPoint userPoint = QUserPoint.userPoint;

    @Override
    public UserResponse.MyPageDto getUserMyPage(Long userId) {
        QUserPoint sub = new QUserPoint("sub");

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserResponse.MyPageDto.class,
                        user.nickName,
                        user.email,
                        user.isPhoneVerified,
                        userPoint.totalPoints
                ))
                .from(user)
                .join(userPoint).on(user.id.eq(userPoint.user.id)).fetchJoin()
                .where(
                        user.id.eq(userId),
                        // 유저의 가장 최신 종합 포인트 서브쿼리
                        userPoint.createdAt.eq(
                                JPAExpressions
                                        .select(sub.createdAt.max())
                                        .from(sub)
                                        .where(sub.user.id.eq(userId))
                        )
                )
                .fetchOne();
    }
}
