package umc.spring.service.UserService;

import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;

public interface UserQueryService {
    Tuple findUserInfoById(Long userId);

    Page<Review> getReviewList(Long userId, Integer page);

    Page<UserMission> getMissionList(Long userId, UserMissionStatus status, Integer page);
}
