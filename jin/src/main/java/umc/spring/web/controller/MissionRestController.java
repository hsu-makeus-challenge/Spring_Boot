package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionByUserConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.service.MissionService.MissionByUserCommandServiceImpl;
import umc.spring.service.MissionService.MissionByUserQueryService;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionCommandServiceImpl;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.validation.annotation.NotAlreadyChallenged;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController{

    public final MissionCommandService missionCommandService;
    private final MissionCommandServiceImpl missionCommandServiceImpl;
    private final MissionByUserQueryService missionByUserQueryService;
    private final MissionByUserCommandServiceImpl missionByUserCommandServiceImpl;

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
}
