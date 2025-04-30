package umc.spring.domain.mission.repository;

import static umc.spring.domain.mission.entity.QMission.mission;
import static umc.spring.domain.store.entity.QStore.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.mission.entity.Mission;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Mission> findAvailableMissionsByLocation(
      Float latitude, Float longitude, Double radius, Pageable pageable) {
    // 하버사인 공식을 사용하여 두 지점 사이의 거리 계산
    NumberExpression<Double> distance =
        Expressions.numberTemplate(
            Double.class,
            "6371 * acos(cos(radians({0})) * cos(radians({1})) * cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1})))",
            latitude,
            store.latitude,
            longitude,
            store.longitude);

    var content =
        queryFactory
            .selectFrom(mission)
            .join(mission.store, store)
            .where(isNotDeleted(), isWithinRadius(distance, radius))
            .orderBy(distance.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    var total =
        queryFactory
            .select(mission.count())
            .from(mission)
            .join(mission.store, store)
            .where(isNotDeleted(), isWithinRadius(distance, radius))
            .fetchOne();

    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression isNotDeleted() {
    return mission.isDeleted.eq(false);
  }

  private BooleanExpression isWithinRadius(NumberExpression<Double> distance, Double radius) {
    return distance.loe(radius);
  }
}
