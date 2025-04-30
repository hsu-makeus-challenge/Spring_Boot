package umc.study.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;
import umc.study.domain.mapping.Review;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final EntityManager entityManager;
    // Or use JPAQueryFactory directly

    @Autowired
    public ReviewRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void writeReview(User user, Store store, float score, String content) {
        Review review = Review.builder()
                .user(user)
                .store(store)
                .score(score)
                .content(content)
                .build();

        entityManager.persist(review);
    }
}
