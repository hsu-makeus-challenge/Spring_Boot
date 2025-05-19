package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController{

    public final MissionCommandService missionCommandService;

    @PostMapping
    public ApiResponse<MissionResponseDTO.AddResultDTO> addMission(
            @RequestParam("storeId") @ExistStores Long storeId,
            @RequestBody @Valid MissionRequestDTO.AddMissionDto request) {

        // 미션 생성, 저장
        Mission mission = missionCommandService.addMission(request,storeId);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }
}
