package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionByUserConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.service.MissionService.MissionByUserQueryService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.user.UserRequestDTO;
import umc.spring.web.dto.user.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final MissionByUserQueryService missionByUserQueryService;
    private final UserQueryService userQueryService;

    @PostMapping("/join")
    @Operation(summary = "유저 회원가입 API",description = "유저가 회원가입하는 API입니다.")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API", description = "유저가 로그인하는 API입니다.")
    public ApiResponse<UserResponseDTO.LoginResultDTO> login(@RequestBody @Valid UserRequestDTO.LoginRequestDTO request) {
        return ApiResponse.onSuccess(userCommandService.loginUser(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<UserResponseDTO.UserInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(userQueryService.getUserInfo(request));
    }

    @GetMapping("/{userId}/missions/ongoing")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "완료되지 않은 미션 목록을 페이징해서 조회합니다.")
    @Parameter(name = "userId")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDto> getUserMissionList
            (@PathVariable(name = "userId") Long userId, @Parameter(name = "page") @ValidPage Integer page){
        Page<MissionByUser> userMissionList = missionByUserQueryService.getMissionByUserList(userId, page);
        return ApiResponse.onSuccess(MissionByUserConverter.userMissionPreViewListDTO(userMissionList));
    }

}
