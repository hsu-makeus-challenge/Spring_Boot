package umc.spring.domain.member.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.dto.MemberSignUpRequestDto;
import umc.spring.domain.member.dto.MemberSignUpResponseDto;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.service.MemberService;
import umc.spring.domain.mission.converter.MissionConverter;
import umc.spring.domain.mission.dto.MissionListResponseDto;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.domain.mission.service.MissionService;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.dto.ReviewResponseDto;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.service.ReviewService;
import umc.spring.global.apiPayload.ApiResponse;
import umc.spring.global.validation.annotation.PageCheck;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원", description = "회원 관련 API")
public class MemberController {
  private final MemberService memberServiceImpl;
  private final ReviewService reviewServiceImpl;
  private final MissionService missionServiceImpl;

  @DeleteMapping("/{memberId}")
  @Operation(summary = "회원 탈퇴", description = "회원 ID를 기준으로 회원 및 관련 데이터를 삭제합니다.")
  public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
    // TODO : 회원 관련 로직 도입시 변경해야 된다.
    Long testMemberId = 1L;
    memberServiceImpl.deleteMemberAndAllRelatedData(testMemberId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/signup")
  @Operation(summary = "회원가입", description = "일반 회원가입 요청을 처리합니다.")
  public ApiResponse<MemberSignUpResponseDto> signup(
      @RequestBody @Valid MemberSignUpRequestDto request) {
    Member newMember = memberServiceImpl.signup(request);
    return ApiResponse.onSuccess(MemberConverter.toSignUpResponseDto(newMember));
  }

  @GetMapping("/mypage/reviews")
  @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "회원 정보를 바탕으로 해당 회원이 작성한 리뷰 목록을 조회합니다.")
  @Parameter(name = "page", description = "페이지 번호 (0부터 시작하지 않고, 1부터 시작)", example = "1")
  public ApiResponse<ReviewResponseDto.ReviewPreViewListDto> getMyReviews(@PageCheck Integer page) {
    Long memberId = 1L; // TODO : 회원 관련 로직 도입시 변경해야 된다.
    Page<Review> reviewList = reviewServiceImpl.getMyReviewList(memberId, page);
    return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDto(reviewList));
  }

  @GetMapping("/mypage/missions")
  @Operation(
      summary = "내 미션 목록 조회",
      description = "회원 정보를 바탕으로 미션 상태(IN_PROGRESS, COMPLETED)에 따라 목록을 필터링하여 조회합니다.")
  @Parameters({
    @Parameter(name = "page", description = "페이지 번호 (1부터 시작)", example = "1"),
    @Parameter(
        name = "status",
        description = "미션 상태 (IN_PROGRESS 또는 COMPLETED), 생략 시 전체",
        example = "IN_PROGRESS")
  })
  public ApiResponse<MissionListResponseDto.MemberMissionsPreViewListDto> getMyMissionsWithStatus(
      @PageCheck Integer page,
      @RequestParam(name = "status", required = false) MissionStatus status) {

    Long memberId = 1L; // TODO : 회원 관련 로직 도입시 변경해야 된다.
    Page<MemberMission> missionList = missionServiceImpl.getMyMissions(memberId, page, status);
    return ApiResponse.onSuccess(MissionConverter.toMemberMissionListResponseDto(missionList));
  }
}
