package umc.spring.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.store.data.Store;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
