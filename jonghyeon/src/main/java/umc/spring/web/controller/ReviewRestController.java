package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.StoreReviewService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/{storeId}/review")
public class ReviewRestController {

    private final StoreReviewService storeReviewService;


    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.writeResultDTO> write(
            @RequestBody @Valid ReviewRequestDTO.WriteReviewDTO request, @PathVariable Long storeId) {
        Review review = storeReviewService.writeReview(request, storeId);
        return ApiResponse.onSuccess(ReviewConverter.towriteResultDTO(review));
    }
}
