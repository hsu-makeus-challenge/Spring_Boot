package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.service.ReviewService.ReviewService;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/stores/{storeId}/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.ReviewResultDto> review(@PathVariable Long userId, @PathVariable @ExistStore Long storeId, @RequestBody @Valid ReviewRequestDTO.ReviewDto request){
        Review review = reviewService.save(userId, storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toDto(review));
    }

}
