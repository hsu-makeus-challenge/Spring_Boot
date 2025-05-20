package umc.spring.domain.store.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.mission.converter.MissionConverter;
import umc.spring.domain.mission.dto.MissionListResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.service.MissionService;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.dto.ReviewResponseDto;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.service.ReviewService;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.global.apiPayload.ApiResponse;
import umc.spring.global.validation.annotation.PageCheck;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "가게", description = "가게 관련 API")
@Validated
public class StoreController {
  private final ReviewService reviewServiceImpl;
  private final MissionService missionServiceImpl;

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
  @Parameter(name = "page", description = "페이지 번호 (0부터 시작하지 않고, 1부터 시작)", example = "1")
  public ApiResponse<ReviewResponseDto.ReviewPreViewListDto> getReviewList(
      @PathVariable(name = "storeId") @ExistStore Long storeId,
      @RequestParam(name = "page") @PageCheck Integer page) {
    Long memberId = 1L;
    Page<Review> reviewList = reviewServiceImpl.getReviewList(memberId, storeId, page);
    return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDto(reviewList));
  }

  @GetMapping("/{storeId}/missions")
  @Operation(
      summary = "가게 미션 목록 조회(페이징 포함)",
      description = "특정 가게의 미션 데이터 목록들을 조회합니다. 이때, 페이징을 포함합니다.")
  @Parameter(name = "storeId", description = "가게 Id, path variable 입니다.", example = "1")
  @Parameter(name = "page", description = "페이지 번호 (0부터 시작하지 않고, 1부터 시작)", example = "1")
  public ApiResponse<MissionListResponseDto.MissionsPreViewListDto> getMissionList(
      @PathVariable(name = "storeId") @ExistStore Long storeId,
      @RequestParam(name = "page") @PageCheck Integer page) {
    Long memberId = 1L;
    Page<Mission> missionList = missionServiceImpl.getMissionList(memberId, storeId, page);
    return ApiResponse.onSuccess(MissionConverter.toMissionListResponseDto(missionList));
  }
}
