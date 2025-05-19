package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.UserConverter;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Users;
import umc.study.domain.mapping.UserMission;
import umc.study.service.UserService.UserCommandService;
import umc.study.service.UserService.UserMissionService;
import umc.study.validation.annotation.AlreadyJoinedMission;
import umc.study.validation.annotation.ExistMission;
import umc.study.web.dto.UserMissionRequestDTO;
import umc.study.web.dto.UserMissionResponseDTO;
import umc.study.web.dto.UserRequestDTO;
import umc.study.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserMissionService userMissionService;

    // Valid앞에 @RequestBody가 있었는데 없어도 되는거 아닌가?(의문)
    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        Users user = userCommandService.joinMember(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    // 가게의 미션 도전하기(도전중 미션으로 추가)
    @PostMapping("/{userId}/mission/{missionId}")
    //@PostMapping("/mission/join")
    public ApiResponse<UserMissionResponseDTO.UserMissionResultDto> join(@PathVariable Integer userId, @PathVariable @AlreadyJoinedMission(userIdParameterName = "userId") Long missionId, @RequestBody @Valid UserMissionRequestDTO.UserMissionDto request){
        UserMission userMission = userMissionService.addMission(userId, missionId, request);
        return ApiResponse.onSuccess(UserMissionConverter.resultDTO(userMission));
    }
}

