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
import umc.spring.domain.MissionRecord;
import umc.spring.service.MissionRecoredService;
import umc.spring.validation.ExistMission;
import umc.spring.validation.ExistPage;
import umc.spring.validation.ExistUser;
import umc.spring.web.dto.MissionRecordRequestDTO;
import umc.spring.web.dto.MissionRecordResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionRecordRestController {

    private final MissionRecoredService missionRecordService;

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
            @Parameter(name = "userId", description = "유저 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionRecordResponseDTO.MissionRecordResultDTO> getMyMissionRecordList(
            @ExistUser @PathVariable Long userId,
            @ExistPage @RequestParam(name = "page") Integer page) {

        Page<MissionRecord> missionRecordList = missionRecordService.getAllMissionByUserId(userId, page);
        return ApiResponse.onSuccess(MissionRecordResponseDTO.toMissionRecordResultDTO(missionRecordList));
    }
}
