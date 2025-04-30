package umc.study.repository.UserRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QPhoneVerification;
import umc.study.domain.QUser;
import umc.study.domain.mapping.QUserMission;
import umc.study.web.dto.HomeInfoDto;
import umc.study.web.dto.MyInfoDto;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPhoneVerification phoneVerification = QPhoneVerification.phoneVerification;
    private final QUser user = QUser.user;
    private final QUserMission userMission = QUserMission.userMission;
    @Override
    public MyInfoDto getMyInfo(Long userId) {
        return jpaQueryFactory
                .select(Projections.constructor(MyInfoDto.class,
                        user.name,
                        user.email,
                        new CaseBuilder()
                                .when(phoneVerification.isVerified.isTrue())
                                .then(phoneVerification.phoneNum)
                                .otherwise("미인증"),
                        user.point
                ))
                .from(user)
                .leftJoin(phoneVerification).on(phoneVerification.user.id.eq(user.id))
                .where(user.id.eq(userId))
                .fetchOne();
    }
    @Override
    public HomeInfoDto getHomeInfo(Long userId) {
        Long cnt = jpaQueryFactory
                .select(userMission.count())
                .from(userMission)
                .where(
                        userMission.isCleared.isTrue(),
                        userMission.user.id.eq(userId))
                .fetchOne();
        int point = jpaQueryFactory.select(user.point).from(user).where(user.id.eq(userId)).fetchOne();
        return new HomeInfoDto(cnt,point);
    }
}
