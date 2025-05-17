package umc.spring.repository.StoreMissionRepository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.config.QueryDSLUtil;
import umc.spring.domain.QMission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.QStoreMission;
import umc.spring.domain.mapping.StoreMission;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class StoreMissionRepositoryImpl implements StoreMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QStoreMission storeMission = QStoreMission.storeMission;
    QMission mission = QMission.mission;

    // 가게의 미션 목록을 페이지네이션으로 조회
    @Override
    public Page<StoreMission> findAllByStoreWithMission(Store store, Pageable pageable) {
        // 1. 정렬 조건 추출
        List<OrderSpecifier<?>> orders = QueryDSLUtil.getOrderSpecifiers(pageable, StoreMission.class, "storeMission");

        // 2. ID 목록 먼저 페이징 조회
        List<Long> storeMissionIds = queryFactory
                .select(storeMission.id)
                .from(storeMission)
                .where(storeMission.store.eq(store))
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (storeMissionIds.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, 0L);
        }

        // 3. ID 기반 fetch join 수행
        List<StoreMission> content = queryFactory
                .selectFrom(storeMission)
                .leftJoin(storeMission.mission, mission).fetchJoin()
                .where(storeMission.id.in(storeMissionIds))
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .distinct()
                .fetch();

        // 4. 전체 개수 조회
        Long count = queryFactory
                .select(storeMission.count())
                .from(storeMission)
                .where(storeMission.store.eq(store))
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null ? count : 0L);
    }
}
