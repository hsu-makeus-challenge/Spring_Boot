package umc.spring.domain.store.controller;

import jakarta.validation.Valid;

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

  @PostMapping("/{storeId}/review")
  @Operation(summary = "가게 리뷰 생성", description = "특정 가게에 사용자 리뷰 데이터를 추가합니다.")
  @Parameter(name = "storeId", description = "상점 Id, path variable 입니다.", example = "1")
  public ApiResponse<ReviewResponseDto.ReviewCreateResult> createReview(
      @RequestBody @Valid ReviewRequestDto request, @PathVariable @ExistStore Long storeId) {
    Long memberId = 1L;
    Review newReview = reviewServiceImpl.createReview(memberId, request, storeId);
    return ApiResponse.onSuccess(StoreConverter.toReviewResponseDto(newReview));
  }
}
