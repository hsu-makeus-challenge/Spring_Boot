package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Mission;
import umc.study.domain.QMission;
import umc.study.domain.QRegion;
import umc.study.domain.QStore;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QUserMission;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QUserMission userMission = QUserMission.userMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public List<UserMission> dynamicQueryWithBooleanBuilder(Long userId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (userId != null) {
            predicate.and(userMission.user.id.eq(Math.toIntExact(userId)));
        }

        predicate.and(userMission.missionStatus.in(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED));

        return queryFactory
                .select(userMission)
                .from(userMission)
                .join(userMission.mission, mission).fetchJoin()
                .join(mission.store, store).fetchJoin()
                .where(predicate)
                .orderBy(
                        userMission.missionStatus
                                .when(MissionStatus.COMPLETED).then(0)
                                .when(MissionStatus.IN_PROGRESS).then(1)
                                .otherwise(2)
                                .asc(),
                        userMission.createdAt.desc(),
                        userMission.id.desc()
                )
                .limit(15)
                .fetch();
    }

    @Override
    public List<Mission> missionsByRegion(Long regionId) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QRegion region = QRegion.region;

        return queryFactory
                .select(mission)
                .from(mission)
                .join(mission.store, store).fetchJoin()
                .join(mission.region, region).fetchJoin()
                .where(
                        region.id.eq(regionId),
                        mission.startDate.loe(LocalDate.now()),
                        mission.endDate.goe(LocalDate.now())
                )
                .orderBy(
                        mission.endDate.asc(),
                        mission.id.asc()
                )
                .fetch();
    }

}
