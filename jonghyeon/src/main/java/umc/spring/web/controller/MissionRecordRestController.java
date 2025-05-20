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
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConveter;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.service.MissionRecordService;
import umc.spring.validation.ExistMission;
import umc.spring.validation.ExistPage;
import umc.spring.validation.ExistUser;
import umc.spring.web.dto.MissionRecordRequestDTO;
import umc.spring.web.dto.MissionRecordResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionRecordRestController {

    private final MissionRecordService missionRecordService;

    @PostMapping("/mission/{missionId}/missionRecords/{userId}")
    public ApiResponse<MissionRecordResponseDTO.MissionRecordResultDTO> addMissionRecord(
            @RequestBody @Valid MissionRecordRequestDTO.addMissionRecordResultDTO request,
            @PathVariable Long missionId,
            @PathVariable Long userId) {

        MissionRecord missionRecord = missionRecordService.addMissionRecord(request, missionId,userId);

        return null;
    }

    @GetMapping("/mission/{userId}")
    @Operation(summary = "유저가 진행 중인 목록 조회 API",description = "유저가 진행중인 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "해당 유저가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, query String 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissionRecordList(
            @ExistUser @PathVariable Long userId,
            @ExistPage Integer page) {

        Page<Mission> missionList = missionRecordService.getInProgressMissionsByUserId(userId,page);
        return ApiResponse.onSuccess(MissionConveter.toMissionListDTO(missionList));
    }

    @PatchMapping("/mission/{missionRecordId}/user/{userId}")
    @Operation(summary = "미션 기록 수정 API",description = "미션 기록을 수정하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "해당 미션 기록이 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "missionRecordId", description = "미션 기록 아이디, path variable 입니다!"),
            @Parameter(name = "userId", description = "유저 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionRecordResponseDTO.MissionRecordUpdateDTO> updateMissionRecord(
            @PathVariable Long missionRecordId,
            @PathVariable Long userId) {

        MissionRecordResponseDTO.MissionRecordUpdateDTO missionRecordUpdateDTO
                = missionRecordService.updateMissionRecord(missionRecordId, userId, MissionRecord.Status.completed);
        return ApiResponse.onSuccess(missionRecordUpdateDTO);
    }


}
