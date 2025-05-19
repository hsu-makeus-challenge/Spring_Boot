package umc.study.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Mission;
import umc.study.service.AdminService.AdminService;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {

    private final AdminService adminService;

    @PostMapping("/store/{storeId}/mission")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> addMission(@PathVariable Long storeId, @RequestBody @Valid MissionRequestDTO.MissionDto request){
        //log.info("Request Body: {}", request.getBody());
        Mission mission = adminService.saveMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toDto(mission));
    }
}
