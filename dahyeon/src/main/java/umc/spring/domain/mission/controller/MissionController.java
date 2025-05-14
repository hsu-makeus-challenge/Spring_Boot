package umc.spring.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.mission.dto.MissionCompleteResponseDto;
import umc.spring.domain.mission.dto.MissionCreateRequestDto;
import umc.spring.domain.mission.dto.MissionCreateResponseDto;
import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.domain.mission.service.MissionService;
import umc.spring.domain.mission.validation.CheckMission;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "미션", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionServiceImpl;

    @Operation(summary = "가게에 미션 추가", description = "특정 가게에 미션을 추가합니다.")
    @PostMapping("/stores/{storeId}/missions")
    @Parameter(name = "storeId", description = "상점 Id, path variable 입니다.", example = "1")
    public ApiResponse<MissionCreateResponseDto> addMissionToStore(
            @PathVariable @ExistStore Long storeId,
            @Valid @RequestBody MissionCreateRequestDto requestDto) {

        Mission mission = missionServiceImpl.createMission(storeId, requestDto);
        return ApiResponse.onSuccess(MissionCreateResponseDto.from(mission));
    }

    @PostMapping("/missions/{missionId}/challenge")
    @Operation(summary = "가게의 미션을 도전중인 미션에 추가하기", description = "도전하려는 미션이 도전중이 아니라면 가게의 미션을 현재 도전중인 미션으로 추가합니다.")
    @Parameter(name="missionId",description = "미션 Id, path variable 입니다.",example = "1")
    public ApiResponse<MissionCompleteResponseDto> addChallengeMission(
            @CheckMission @PathVariable("missionId") Long missionId
    ){
        Long memberId = 1L;
        MemberMission memberMission = missionServiceImpl.createChallenge(missionId, memberId);
        return ApiResponse.onSuccess(MissionCompleteResponseDto.from(memberMission));
    }
}
