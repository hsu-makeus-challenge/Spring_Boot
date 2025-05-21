package umc.study.service.ReviewService;

import umc.study.domain.*;
import umc.study.domain.mapping.Review;
import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewService {
    public Review writeReview(ReviewRequestDTO.AddDto request,User user,Store store);
}
