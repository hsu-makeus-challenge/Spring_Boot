package umc.spring.repository.UserMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.QStoreMission;
import umc.spring.domain.mapping.QUserMission;
import umc.spring.domain.mapping.UserMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    private final QUserMission userMission = QUserMission.userMission;
    private final QStoreMission storeMission = QStoreMission.storeMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public Page<UserMission> dynamicQueryWithBooleanBuilder(Long userId, UserMissionStatus userMissionStatus, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(userMission.user.id.eq(userId));
        predicate.and(userMission.status.eq(userMissionStatus));

        List<UserMission> content = jpaQueryFactory
                .selectFrom(userMission)
                .join(userMission.storeMission, storeMission).fetchJoin()
                .join(storeMission.mission, mission).fetchJoin()
                .join(storeMission.store, store).fetchJoin()
                .where(predicate)
                .orderBy(userMission.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // total count 계산 (전체 데이터 개수)
        Long total = jpaQueryFactory
                .select(userMission.count())
                .from(userMission)
                .where(predicate)
                .fetchOne();

        // null 체크
        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
