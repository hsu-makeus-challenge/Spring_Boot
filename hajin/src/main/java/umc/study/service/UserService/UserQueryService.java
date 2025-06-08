package umc.study.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.UserResponseDTO;

public interface UserQueryService {
    Page<Review> getReviewList(Integer userId, Integer page);
    UserResponseDTO.UserInfoDTO getUserInfo(HttpServletRequest request);
}
