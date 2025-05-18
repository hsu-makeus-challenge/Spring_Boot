package umc.spring.domain.store.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.dto.ReviewResponseDto;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.service.ReviewService;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "가게", description = "가게 관련 API")
@Validated
public class StoreController {
  private final ReviewService reviewServiceImpl;

  @PostMapping("/{storeId}/reviews")
  @Operation(summary = "가게 리뷰 생성", description = "특정 가게에 사용자 리뷰 데이터를 추가합니다.")
  @Parameter(name = "storeId", description = "가게 Id, path variable 입니다.", example = "1")
  public ApiResponse<ReviewResponseDto.ReviewCreateResultDto> createReview(
      @RequestBody @Valid ReviewRequestDto request, @PathVariable @ExistStore Long storeId) {
    Long memberId = 1L;
    Review newReview = reviewServiceImpl.createReview(memberId, request, storeId);
    return ApiResponse.onSuccess(StoreConverter.toReviewResponseDto(newReview));
  }

  @GetMapping("/{storeId}/reviews")
  @Operation(summary = "가게 리뷰 조회(페이징 포함)", description = "특정 가게의 리뷰 데이터들을 조회합니다. 이때, 페이징을 포함합니다.")
  @Parameter(name = "storeId", description = "가게 Id, path variable 입니다.", example = "1")
  public ApiResponse<ReviewResponseDto.ReviewPreViewListDto> getReviewList(
      @PathVariable(name = "storeId") @ExistStore Long storeId,
      @RequestParam(name = "page") Integer page) {
    Long memberId = 1L;
    Page<Review> reviewList = reviewServiceImpl.getReviewList(memberId, storeId, page);
    return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDto(reviewList));
  }
}
