package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewService {
    public Review save(Long userId, Long storeId, ReviewRequestDTO.ReviewDto request);
}
