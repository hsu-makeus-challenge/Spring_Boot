package umc.spring.domain.review.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.review.converter.ReviewConverter;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.service.ReviewCommandService;
import umc.spring.domain.review.web.dto.ReviewRequestDTO;
import umc.spring.domain.review.web.dto.ReviewResponseDTO;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.global.common.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.addResultDto> addReview(@RequestBody @Valid ReviewRequestDTO.addDto request, @RequestParam("storeId") @ExistStore Long storeId){
        Review review = reviewCommandService.createReview(request, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toResultDto(review));
    }

}
