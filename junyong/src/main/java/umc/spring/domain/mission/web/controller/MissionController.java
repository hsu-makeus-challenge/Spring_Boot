package umc.spring.domain.mission.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.member.converter.MemberMissionConverter;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.service.MemberMissionCommandService;
import umc.spring.domain.mission.converter.MissionConverter;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.service.MissionCommandService;
import umc.spring.domain.mission.validation.annotation.MemberMissionExist;
import umc.spring.domain.mission.web.dto.MissionRequestDTO;
import umc.spring.domain.mission.web.dto.MissionResponseDTO;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.global.common.apiPayload.ApiResponse;
import umc.spring.global.common.apiPayload.code.status.SuccessStatus;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.AddResultDto> addMission(
            @RequestBody @Valid MissionRequestDTO.addDto request, @RequestParam("storeId") @ExistStore Long storeId
    ) {
        Mission mission = missionCommandService.addMission(request, storeId);
        return ApiResponse.onSuccess(MissionConverter.addResultDto(mission));
    }

    @PostMapping("/{missionId}")
    public ApiResponse<MissionResponseDTO.ChallengeResultDto> challengeMission(@PathVariable @MemberMissionExist Long missionId) {
        MemberMission memberMission = memberMissionCommandService.challengeMission(missionId);
        return ApiResponse.onSuccess(SuccessStatus.MISSION_CHALLENGE, MemberMissionConverter.toChallengeResultDto(memberMission));
    }

}
