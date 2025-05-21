package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.mapping.UserMission;
import umc.study.service.UserMissionService.UserMissionQueryService;
import umc.study.service.UserMissionService.UserMissionQueryServiceImpl;
import umc.study.validation.annotation.ValidPageNum;
import umc.study.web.dto.UserMissionResponseDTO;

@RestController
@Validated
@RequestMapping("/myMissions")
@RequiredArgsConstructor
public class UserMissionRestController {
    private final UserMissionQueryService userMissionQueryService;

    @GetMapping
    @Operation(summary = "내 미션 목록 조회 API",description = "페이징 포함 내 미션 목록 조회 API입니다. query String 페이지 번호(1부터 시작)" )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<UserMissionResponseDTO.UserMissionPreviewListDTO> getMyMissionList(@ValidPageNum Integer page){
        Page<UserMission> myMissionList = userMissionQueryService.getMyClearedMissions(page);
        return ApiResponse.onSuccess(UserMissionConverter.toUserMissionPreviewListDTO(myMissionList));
    }

    @PatchMapping("/{userMissionId}/complete")
    @Operation(summary = "미션 완료 처리 API", description = "지정한 유저 미션을 완료 상태로 바꿉니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOT_FOUND", description = "존재하지 않는 유저 미션", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ApiResponse<String> completeMission(@PathVariable Long userMissionId) {
        userMissionQueryService.clearMission(userMissionId);
        return ApiResponse.onSuccess(String.valueOf(userMissionId)+"번 미션 완료!");
    }



}
