package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.service.UserMissionService.UserMissionQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStoreMission;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.userMission.UserMissionResponse;


@Tag(name = "유저 페이지", description = "유저에 관한 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ReviewQueryService reviewQueryService;
    private final UserMissionCommandService userMissionCommandService;
    private final UserMissionQueryService userMissionQueryService;

    @Operation(
            summary = "유저의 리뷰 목록 조회",
            description = "유저가 작성한 리뷰 목록을 페이지네이션으로 조회하는 API 입니다. 페이지 번호는 양수여야 합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "아이디와 일치하는 사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 비어있습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4002", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "로그인한 유저의 ID", example = "1", required = true),
            @Parameter(name = "page", description = "페이지 번호, 1부터 시작입니다.", example = "1", required = true)
    })
    @GetMapping("/{userId}/review")
    public ApiResponse<ReviewResponse.UserReviewPreViewListDto> getUserReviewList(@PathVariable(name="userId") @ExistUser Long userId,
                                                                                   @CheckPage Integer page) {

        ReviewResponse.UserReviewPreViewListDto response = reviewQueryService.findUserReviewPage(userId, page);

        return ApiResponse.onSuccess(response);
    }

    @Operation(
            summary = "status에 따른 유저의 미션 목록 조회",
            description = "유저의 미션 목록을 미션 상태별로 조회하 페이지네이션으로 조회하는 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "아이디와 일치하는 사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 비어있습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4002", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "로그인한 유저의 ID", example = "1", required = true),
            @Parameter(name = "page", description = "페이지 번호, 1부터 시작입니다.", example = "1", required = true),
            @Parameter(name = "status", description = "미션 상태. ONGOING: 진행 중, COMPLETED: 진행 완료", example = "ONGOING", required = true
            )
    })
    @GetMapping("/{userId}/userMissions")
    public ApiResponse<UserMissionResponse.UserMissionInfoListDto> getUserMissionPageByStatus(@PathVariable(name="userId") @ExistUser Long userId,
                                                                                                  @RequestParam(name="status") MissionStatus status,
                                                                                                  @CheckPage Integer page) {

        UserMissionResponse.UserMissionInfoListDto response = userMissionQueryService.findUserMissionPageByStatus(userId, status, page);

        return ApiResponse.onSuccess(response);
    }


    @Operation(
            summary = "미션 도전하기 및 미션 성공 누르기",
            description = "가게의 미션을 도전 중인 미션에 추가하기 및 미션 성공 누르기 API 입니다." +
                    "status에 NOT_STARTED 입력 시 미션 도전하기, SUCCEED 입력 시 미션 성공 누르기 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "아이디와 일치하는 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE_MISSION4002", description = "이미 존재하는 가게 미션 입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping("/{userId}/storeMissions/{storeMissionId}")
    public ApiResponse<UserMissionResponse.UserMissionResultDto> patchUserMissionByStatus(
            @Parameter(description = "가게의 미션 아이디", example = "1", required = true)
            @PathVariable @ExistStoreMission Long storeMissionId,

            @Parameter(description = "유저 아이디", example = "1", required = true)
            @PathVariable @ExistUser Long userId,

            @Parameter(
                    name = "status",
                    description = "미션 상태. CHALLENGE: 도전하기, SUCCEED: 성공 처리",
                    example = "CHALLENGE",
                    required = true
            )
            @RequestParam(name="status") MissionStatus status
    ) {
        UserMissionResponse.UserMissionResultDto response;

        switch (status) {
            case CHALLENGE -> response = userMissionCommandService.saveUserMission(storeMissionId, userId);
            case SUCCEEDED -> {
                response = null;
            }
            default -> {
                // 정의되지 않은 상태일 경우 예외 처리 필요
                response = null;
            }
        }

        return ApiResponse.onSuccess(response);
    }
}
