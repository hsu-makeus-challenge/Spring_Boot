package umc.spring.repository.StoreMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.*;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.*;
import umc.spring.repository.UserMissionRepository.UserMissionRepositoryCustom;

import java.util.List;
import java.util.stream.Collectors;

import static umc.spring.domain.mapping.QStoreFoodCategory.storeFoodCategory;

@Repository
@RequiredArgsConstructor
public class StoreMissionRepositoryImpl implements StoreMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QNeighborhood neighborhood = QNeighborhood.neighborhood1;
    private final QStore store = QStore.store;
    private final QStoreMission storeMission = QStoreMission.storeMission;
    private final QMission mission = QMission.mission;
    private final QStoreFoodCategory sfc = storeFoodCategory;
    private final QFoodCategory foodCategory = QFoodCategory.foodCategory;
    private final QUserMission userMission = QUserMission.userMission;

    @Override
    public Page<StoreMission> dynamicQueryWithBooleanBuilder(Long neighborhoodId, Long userId, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(store.neighborhood.id.eq(neighborhoodId));
        predicate.and(storeMission.id.notIn(
                    JPAExpressions.select(userMission.storeMission.id)
                            .from(userMission)
                            .where(userMission.user.id.eq(userId))
        ));

        // 1. StoreMission과 1:1 관계만 fetch join
        List<StoreMission> storeMissions = jpaQueryFactory
                .selectFrom(storeMission)
                .join(storeMission.store, store).fetchJoin()
                .join(store.neighborhood, neighborhood).fetchJoin()
                .join(storeMission.mission, mission).fetchJoin()
                .where(predicate)
                .orderBy(store.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. 조회된 store ID 목록
        List<Long> storeIds = storeMissions.stream()
                .map(sm -> sm.getStore().getId())
                .distinct()
                .collect(Collectors.toList());

        // 3. 추가로 StoreFoodCategory 조회
        if (!storeIds.isEmpty()) {
            List<Store> storesWithCategories = jpaQueryFactory
                    .selectFrom(store)
                    .join(store.storeFoodCategoryList, sfc).fetchJoin()
                    .join(sfc.foodCategory, foodCategory).fetchJoin()
                    .where(store.id.in(storeIds))
                    .fetch();
        }

        // 4. total count 계산 (전체 데이터의 개수)
        Long total = jpaQueryFactory
                .select(storeMission.count())
                .from(storeMission)
                .join(storeMission.store, store)
                .join(store.neighborhood, neighborhood)
                .where(predicate)
                .fetchOne();

        // null 체크
        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(storeMissions, pageable, totalCount);
    }
}
