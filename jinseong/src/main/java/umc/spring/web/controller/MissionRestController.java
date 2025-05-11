package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreMissionConverter;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.StoreMissionService.StoreMissionCommandService;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.NotExistUserMission;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring.web.dto.MissionDTO.MissionResponseDTO;

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
}
