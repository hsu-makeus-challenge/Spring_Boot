package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByStoreAndUser(Store store, User user);

    Page<Review> findAllByStoreAndUser(Store store, User user, PageRequest of);
}
