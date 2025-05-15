package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.validation.annotation.ExistStoreMission;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.userMission.UserMissionResponse;

@Tag(name = "유저 미션 페이지", description = "사용자들의 미션에 관한 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeMissions")
public class UserMissionController {

    private final UserMissionCommandService userMissionCommandService;

    @Operation(
            summary = "미션 도전하기 및 미션 성공 누르기",
            description = "가게의 미션을 도전 중인 미션에 추가하기 및 미션 성공 누르기 API 입니다." +
            "status에 NOT_STARTED 입력 시 미션 도전하기, COMPLETED 입력 시 미션 성공 누르기 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "아이디와 일치하는 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE_MISSION4002", description = "이미 존재하는 가게 미션 입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping("/{storeMissionId}/users/{userId}")
    public ApiResponse<UserMissionResponse.UserMissionCreateResultDto> patchUserMissionByStatus(
            @Parameter(description = "가게의 미션 아이디", example = "1", required = true)
            @PathVariable @ExistStoreMission Long storeMissionId,

            @Parameter(description = "유저 아이디", example = "1", required = true)
            @PathVariable @ExistUser Long userId,

            @Parameter(
                    name = "status",
                    description = "미션 상태. NOT_STARTED: 도전하기, COMPLETED: 성공 처리",
                    example = "NOT_STARTED",
                    required = true
            )
            @RequestParam(name="status") MissionStatus status
    ) {
        UserMissionResponse.UserMissionCreateResultDto response;

        switch (status) {
            case NOT_STARTED -> response = userMissionCommandService.saveUserMission(storeMissionId, userId);
            case COMPLETED -> {
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
