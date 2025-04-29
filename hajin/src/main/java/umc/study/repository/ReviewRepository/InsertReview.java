package umc.study.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.Users;

@Repository
public class InsertReview {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insertReview(Long userId, Long storeId, String content, Float score) {
        Users user = em.getReference(Users.class, userId);
        Store store = em.getReference(Store.class, storeId);

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(content)
                .score(score) // 별점 추가
                .build();

        em.persist(review);
    }
}
