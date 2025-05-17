package umc.spring.service.UserService;

import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface UserQueryService {
    Tuple findUserInfoById(Long userId);

    Page<Review> getReviewList(Long userId, Integer page);
}
