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
import umc.spring.validation.annotation.ExistUserMission;
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
            summary = "미션 도전하기",
            description = "가게의 미션을 도전 중인 미션에 추가하기 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "아이디와 일치하는 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE_MISSION4002", description = "이미 존재하는 가게 미션 입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeMissionId", description = "가게 미션 ID", example = "1", required = true),
            @Parameter(name = "userId", description = "유저 ID", example = "1", required = true),
    })
    @PostMapping("/{userId}/storeMissions/{storeMissionId}")
    public ApiResponse<UserMissionResponse.UserMissionResultDto> postUserMission(@PathVariable(name="userId") @ExistUser Long userId,
                                                                                 @PathVariable(name="storeMissionId") @ExistStoreMission Long storeMissionId)
    {
        UserMissionResponse.UserMissionResultDto response = userMissionCommandService.saveUserMission(userId, storeMissionId);

        return ApiResponse.onSuccess(response);
    }


    @Operation(
            summary = "미션 성공 누르기",
            description = "진행중인 미션을 성공으로 처리하는 API입니다. status에 SUCCEED를 입력해 주세요."
    )
    @Parameters({
            @Parameter(name = "userId", description = "유저 ID", example = "1", required = true),
            @Parameter(name = "userMissionId", description = "유저 미션 ID", example = "1", required = true),
            @Parameter(name = "status", description = "미션 상태 (SUCCEED)", example = "SUCCEED", required = true)
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "아이디와 일치하는 사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USR_MISSION4001", description = "아이디와 일치하는 유저 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USR_MISSION4003", description = "진행중인 미션이 아닙니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PatchMapping("/{userId}/userMission/{userMissionId}")
    public ApiResponse<UserMissionResponse.UserMissionResultDto> patchUserMissionByStatus(@PathVariable @ExistUser Long userId,
                                                                                          @PathVariable @ExistUserMission Long userMissionId,
                                                                                          @RequestParam(name = "status") MissionStatus status
    ) {
        UserMissionResponse.UserMissionResultDto response = userMissionCommandService.updateUserMissionStatus(userId, userMissionId, status);

        return ApiResponse.onSuccess(response);
    }
}
