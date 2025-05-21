package umc.spring.repository.UserMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.config.QueryDSLUtil;
import umc.spring.domain.QFoodCategory;
import umc.spring.domain.QMission;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QStoreFoodCategory;
import umc.spring.domain.mapping.QStoreMission;
import umc.spring.domain.mapping.QUserMission;
import umc.spring.domain.mapping.UserMission;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QUserMission userMission = QUserMission.userMission;
    private final QStoreMission storeMission = QStoreMission.storeMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;
    private final QStoreFoodCategory storeFoodCategory = QStoreFoodCategory.storeFoodCategory;
    private final QFoodCategory foodCategory = QFoodCategory.foodCategory;

    // 홈 화면 - 미션 상태에 따른 미션 목록 조회
    @Override
    public Page<UserMission> findUserMissionsByStatus(Long userId, MissionStatus status, Pageable pageable) {
        // 조건 builder 생성
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(userMission.user.id.eq(userId));

        // 파라미터가 진행 완료일 시 성공 or 실패 미션 조회
        if (status == MissionStatus.COMPLETED) {
            builder.and(
                    userMission.missionStatus.eq(MissionStatus.SUCCEEDED)
                            .or(userMission.missionStatus.eq(MissionStatus.FAILED))
            );
        } else {
            builder.and(userMission.missionStatus.eq(status));
        }

        // order
        List<OrderSpecifier<?>> orders = QueryDSLUtil.getOrderSpecifiers(pageable, UserMission.class, "userMission");

        // 데이터 조회
        List<UserMission> results = jpaQueryFactory
                .selectFrom(userMission)
                .join(userMission.storeMission, storeMission).fetchJoin()
                .join(storeMission.mission, mission).fetchJoin()
                .join(storeMission.store, store).fetchJoin()
                .where(builder)
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 조회
        Long total = jpaQueryFactory
                .select(userMission.count())
                .from(userMission)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

    // 선택된 지역에서 시작 전 NOT_STARTED 미션 조회
    @Override
    public Page<UserMission> findUserMissionsByRegionAndStatus(Long userId, Long regionId, MissionStatus status, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(userMission.user.id.eq(userId));
        builder.and(userMission.missionStatus.eq(status));
        builder.and(store.region.id.eq(regionId));

        List<OrderSpecifier<?>> orders = QueryDSLUtil.getOrderSpecifiers(pageable, UserMission.class, "userMission");

        List<UserMission> results = jpaQueryFactory
                .selectFrom(userMission).distinct()
                .join(userMission.storeMission, storeMission).fetchJoin()
                .join(storeMission.mission, mission).fetchJoin()
                .join(storeMission.store, store).fetchJoin()
                .join(store.storeFoodCategoryList, storeFoodCategory).fetchJoin()
                .join(storeFoodCategory.foodCategory, foodCategory).fetchJoin()
                .where(builder)
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(userMission.id.countDistinct())
                .from(userMission)
                .join(userMission.storeMission, storeMission)
                .join(storeMission.store, store)
                .join(store.region, region)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

    // 최신 10개의 유저 미션 데이터 중 성공인 미션만 count
    @Override
    public Long getSucceededMissionCount(Long userId, Long regionId) {
        return jpaQueryFactory
                .select(userMission.count())
                .from(userMission)
                .join(userMission.storeMission, storeMission)
                .join(storeMission.store, store)
                .join(store.region, region)
                .where(
                        userMission.user.id.eq(userId),
                        userMission.missionStatus.eq(MissionStatus.SUCCEEDED),
                        store.region.id.eq(regionId)
                )
                .orderBy(userMission.createdAt.desc())
                .limit(10)
                .fetchOne();
    }

    // 선택된 지역에서 도전 가능한 미션 조회
//    @Override
//    public Page<MissionResponse.MissionInfoDto> findUserMissionsByRegionAndStatus(Long userId, Long regionId, MissionStatus status, Pageable pageable) {
//        // 조건 builder 생성
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(userMission.user.id.eq(userId));
//        builder.and(userMission.missionStatus.eq(status));
//        builder.and(store.region.id.eq(regionId));
//
//        // order
//        List<OrderSpecifier<?>> orders = QueryDSLUtil.getOrderSpecifiers(pageable, UserMission.class, "userMission");
//
//        // 데이터 조회 (DTO로 바로 매핑)
//        List<MissionResponse.MissionInfoDto> results = jpaQueryFactory
//                .select(Projections.constructor(
//                        MissionResponse.MissionInfoDto.class,
//                        userMission.id,
//                        store.storeName,
//                        ExpressionUtils.as(
//                                JPAExpressions.select(
//                                                foodCategory.foodCategoryName
//                                        )
//                                        .from(storeFoodCategory)
//                                        .join(storeFoodCategory.foodCategory, foodCategory)
//                                        .where(storeFoodCategory.store.id.eq(store.id))
//                                        .fetchAll() // 리스트 반환
//                                , "foodCategoryList"),
//                        mission.missionMoney,
//                        mission.reward,
//                        userMission.missionStatus
//                ))
//                .from(userMission)
//                .join(userMission.storeMission, storeMission)
//                .join(storeMission.mission, mission)
//                .join(storeMission.store, store)
//                .where(builder)
//                .orderBy(orders.toArray(new OrderSpecifier[0]))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        // 전체 개수 조회
//        Long total = jpaQueryFactory
//                .select(userMission.id.countDistinct())
//                .from(userMission)
//                .join(userMission.storeMission, storeMission)
//                .join(storeMission.store, store)
//                .join(store.region, region)
//                .where(builder)
//                .fetchOne();
//
//        return new PageImpl<>(results, pageable, total);
//    }
}