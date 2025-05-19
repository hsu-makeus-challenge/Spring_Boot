package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.MissionRecord;
import umc.spring.service.MissionRecoredService;
import umc.spring.web.dto.MissionRecordRequestDTO;
import umc.spring.web.dto.MissionRecordResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission/{missionId}/missionRecords/{userId}")
public class MissionRecordRestController {

    private final MissionRecoredService missionRecordService;

    @PostMapping("/")
    public ApiResponse<MissionRecordResponseDTO.MissionRecordResultDTO> addMissionRecord(
            @RequestBody @Valid MissionRecordRequestDTO.addMissionRecordResultDTO request,
            @PathVariable Long missionId,
            @PathVariable Long userId) {

        MissionRecord missionRecord = missionRecordService.addMissionRecord(request, missionId,userId);

        return null;
    }
}
