package umc.study.repository.UserMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QMission;
import umc.study.domain.QStore;
import umc.study.domain.mapping.QUserMission;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserMission userMission = QUserMission.userMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public List<UserMissionDto> findClearedMissionsWithStore() {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(userMission.isCleared.isTrue());

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserMissionDto.class,
                        userMission.id,
                        mission.point,
                        store.name,
                        mission.context,
                        userMission.isCleared
                ))
                .from(userMission)
                .join(userMission.mission, mission)
                .join(mission.store, store)
                .where(predicate)
                .fetch();

    }

    @Override
    public List<UserMissionDto> findNotClearedMissionsWithStore() {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(userMission.isCleared.isFalse());

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserMissionDto.class,
                        userMission.id,
                        mission.point,
                        store.name,
                        mission.context,
                        userMission.isCleared
                ))
                .from(userMission)
                .join(userMission.mission, mission)
                .join(mission.store, store)
                .where(predicate)
                .fetch();
    }

}
