package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreMissionConverter;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.StoreMissionService.StoreMissionCommandService;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.NotExistUserMission;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring.web.dto.MissionDTO.MissionResponseDTO;
import umc.spring.web.dto.UserDTO.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionRestController {

    private final StoreMissionCommandService storeMissionCommandService;
    private final UserMissionCommandService userMissionCommandService;

    @PostMapping("/stores/{storeId}")
    public ApiResponse<MissionResponseDTO.CreateStoreMissionResultDTO> createSM(
            @ExistStore @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDTO.CreateStoreMissionDTO request) {
        StoreMission storeMission = storeMissionCommandService.createStoreMission(storeId, request);
        return ApiResponse.onSuccess(StoreMissionConverter.toCreateStoreMissionResultDTO(storeMission));
    }

    @PostMapping("/users/{storeMissionId}")
    public ApiResponse<MissionResponseDTO.CreateUserMissionResultDTO> createUM(
            @NotExistUserMission @PathVariable Long storeMissionId) {
        UserMission userMission = userMissionCommandService.createUserMission(1L, storeMissionId);
        return ApiResponse.onSuccess(UserMissionConverter.toCreateUserMissionResultDTO(userMission));
    }

    @PatchMapping("/{userMissionId}")
    @Operation(summary = "미션 완료 API", description = "진행 중인 미션을 진행 완료 상태로 바꾸는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "UM4002", description = "사용자 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "UM4003", description = "진행 중인 미션이 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "UM4004", description = "미션 코드가 다릅니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userMissionId", description = "사용자 미션 ID", required = true, in = ParameterIn.PATH, example = "1"),
    })
    public ApiResponse<MissionResponseDTO.CompleteUserMissionResultDTO> getMissionList(
            @PathVariable Long userMissionId,
            @Valid @RequestBody MissionRequestDTO.CompleteUserMissionDTO request) {

        UserMission result = userMissionCommandService.completeUserMission(userMissionId, request.getCode());

        return ApiResponse.onSuccess(UserMissionConverter.completeUserMissionResultDTO(result));
    }
}
