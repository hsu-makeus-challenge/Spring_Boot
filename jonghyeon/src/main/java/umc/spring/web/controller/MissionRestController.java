package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConveter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
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
}