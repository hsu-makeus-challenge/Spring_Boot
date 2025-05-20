package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionByUserConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.service.MissionService.MissionByUserCommandServiceImpl;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.validation.annotation.NotAlreadyChallenged;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController{

    public final MissionCommandService missionCommandService;
    private final MissionByUserCommandServiceImpl missionByUserCommandServiceImpl;
    private final MissionQueryService missionQueryService;

    @PostMapping
    public ApiResponse<MissionResponseDTO.AddResultDTO> addMission(
            @RequestParam("storeId") @ExistStores Long storeId,
            @RequestBody @Valid MissionRequestDTO.AddMissionDto request) {

        // 미션 생성, 저장
        Mission mission = missionCommandService.addMission(request,storeId);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }


    @PostMapping("/{missionId}")
    public ApiResponse<MissionResponseDTO.ChallengeResultDTO> challengeMission(
            @PathVariable("missionId") @NotAlreadyChallenged Long missionId) {

        MissionByUser missionByUser = missionByUserCommandServiceImpl.challengeMission(missionId);

        return ApiResponse.onSuccess(MissionByUserConverter.toChallengeResultDTO(missionByUser));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에 등록된 미션 목록을 조회합니다.")
    @Parameter(name = "storeId")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDto> getMissionList
            (@ExistStores @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = missionQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
    }

}
