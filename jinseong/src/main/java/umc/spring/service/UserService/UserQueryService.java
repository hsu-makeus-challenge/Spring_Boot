package umc.spring.service.UserService;

import com.querydsl.core.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserDTO.UserResponseDTO;

public interface UserQueryService {
    Tuple findUserInfoById(Long userId);

    Page<Review> getReviewList(Long userId, Integer page);

    Page<UserMission> getMissionList(Long userId, UserMissionStatus status, Integer page);

    UserResponseDTO.UserInfoDTO getUserInfo(HttpServletRequest request);
}
