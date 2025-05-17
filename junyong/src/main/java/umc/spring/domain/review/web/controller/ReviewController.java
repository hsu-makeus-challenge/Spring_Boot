package umc.spring.domain.review.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.review.converter.ReviewConverter;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.service.ReviewCommandService;
import umc.spring.domain.review.service.ReviewQueryService;
import umc.spring.domain.review.web.dto.ReviewRequestDTO;
import umc.spring.domain.review.web.dto.ReviewResponseDTO;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.global.common.apiPayload.ApiResponse;
import umc.spring.global.common.validation.annotation.PageValid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.addResultDto> addReview(@RequestBody @Valid ReviewRequestDTO.addDto request, @RequestParam("storeId") @ExistStore Long storeId){
        Review review = reviewCommandService.createReview(request, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toResultDto(review));
    }

    // 9주차
    @GetMapping
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 조회합니다. 페이징을 포함합니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호. 1이상. Query String")
    })
    public ApiResponse<ReviewResponseDTO.ReviewListDto> getReviews(@PageValid Integer page) {
        ReviewResponseDTO.ReviewListDto reviewDtoList = reviewQueryService.getReviews(page);
        return ApiResponse.onSuccess(reviewDtoList);
    }


}
