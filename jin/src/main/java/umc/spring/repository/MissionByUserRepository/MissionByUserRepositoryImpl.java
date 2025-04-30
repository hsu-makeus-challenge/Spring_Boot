package umc.spring.repository.MissionByUserRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.domain.mapping.QMissionByUser;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionByUserRepositoryImpl implements MissionByUserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMissionByUser missionByUser = QMissionByUser.missionByUser;

    @Override
    public List<MissionByUser> dynamicQueryWithBooleanBuilder(Long userNo, Boolean isCompleted) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(userNo != null){
            // MissionByUser 엔터티에 연관된 User 의 객체의 id
            predicate.and(missionByUser.user.id.eq(userNo));

        }
        if(isCompleted != null){
            predicate.and(missionByUser.isCompleted.eq(isCompleted));
        }

        return jpaQueryFactory
                .selectFrom(missionByUser)
                .where(predicate)
                .fetch();
    }
}
