package umc.spring.domain.review.service;

import umc.spring.domain.review.web.dto.ReviewResponseDTO;

public interface ReviewQueryService {

    ReviewResponseDTO.ReviewListDto getReviews(Integer page);

}
