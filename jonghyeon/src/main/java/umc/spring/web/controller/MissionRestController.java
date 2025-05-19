package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConveter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService;
import umc.spring.validation.ExistPage;
import umc.spring.validation.ExistStore;
import umc.spring.validation.ExistUser;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/store/{storeId}/mission")
public class MissionRestController {

    private final MissionService missionService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.addMissionResultDTO> join(
            @RequestBody @Valid MissionRequestDTO.addMissionDTO request,
            @PathVariable Long storeId) {

        Mission mission = missionService.addMission(request, storeId);

        return ApiResponse.onSuccess(MissionConveter.toAddMissionResultDTO(mission));
    }

    @GetMapping("/")
    @Operation(summary = "가게의 미션 목록 조회 API",description = "가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "해당 가게에 미션이 존재하지 않습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "가게가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MissionResponseDTO.storeMissionListDTO> getMyReviewList(
            @ExistStore @PathVariable(name="storeId") Long storeId,
            @ExistPage @RequestParam(name = "page") Integer page
    ) {
        Page<Mission> MissionList = missionService.getAllMissionsByStoreId(storeId, page);
        return ApiResponse.onSuccess(MissionConveter.toMissionListDTO(MissionList));
    }
}