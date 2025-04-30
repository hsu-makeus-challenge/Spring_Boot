package umc.study.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.mapping.Review;
import java.util.List;

public interface ReviewRepositoryCustom{
    void writeReview(User user, Store store, float score, String content);
}
