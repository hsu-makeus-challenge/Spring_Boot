package umc.spring.domain.member.repository.memberMission;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.data.mapping.QMemberMission;
import umc.spring.domain.mission.data.QMission;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.domain.region.data.Region;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberMission> findMemberMissonList(MissionStatus missionStatus, int offset, int limit) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (missionStatus != null) {
            booleanBuilder.and(memberMission.status.eq(missionStatus));
        }

        return jpaQueryFactory
                .selectFrom(memberMission)
                .where(booleanBuilder)
                .offset(offset) // 페이징
                .limit(limit)
                .fetch();
    }

    @Override
    public List<MemberMission> findMemberMissionByRegion(Region region, MissionStatus status) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(mission.region.eq(region));
        booleanBuilder.and(memberMission.status.eq(status));

        return jpaQueryFactory
                .selectFrom(memberMission)
                .join(mission)
//                .where(mission.region.eq(region))
//                .where(memberMission.status.eq(MissionStatus.COMPLETE))
                .where(booleanBuilder)
                .fetch();
    }
}
