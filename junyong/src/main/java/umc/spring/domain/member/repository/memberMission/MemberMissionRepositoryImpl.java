package umc.spring.domain.member.repository.memberMission;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.data.mapping.QMemberMission;
import umc.spring.domain.mission.data.enums.MissionStatus;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final QMemberMission memberMission = QMemberMission.memberMission;
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
}
