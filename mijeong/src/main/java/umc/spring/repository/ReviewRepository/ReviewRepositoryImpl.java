package umc.spring.repository.ReviewRepository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import umc.spring.config.QueryDSLUtil;
import umc.spring.domain.*;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QReview review = QReview.review;
    QReviewImage reviewImage = QReviewImage.reviewImage;

    @Override
    public Page<Review> findAllByUserWithImage(User user, Pageable pageable) {
        // 1. 정렬 조건 추출
        List<OrderSpecifier<?>> orders = QueryDSLUtil.getOrderSpecifiers(pageable, Review.class, "review");

        // 2. ID 목록 먼저 페이징 조회
        List<Long> reviewIds = queryFactory
                .select(review.id)
                .from(review)
                .where(review.user.eq(user))
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (reviewIds.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, 0L);
        }

        // 3. ID 기반 fetch join 수행
        List<Review> content = queryFactory
                .selectFrom(review)
                .leftJoin(review.reviewImageList, reviewImage).fetchJoin()
                .where(review.id.in(reviewIds))
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .distinct()
                .fetch();

        // 4. 전체 개수 조회
        Long count = queryFactory
                .select(review.count())
                .from(review)
                .where(review.user.eq(user))
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null ? count : 0L);
    }
}
