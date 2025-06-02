package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.OAuthService.OAuthService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

@Tag(name = "로그인/회원가입", description = "인증에 관한 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserCommandService userCommandService;
    private final OAuthService oAuthService;

    // 회원가입
    @Operation(
            summary = "회원가입",
            description = "회원가입 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "FOOD_CATEGORY4001", description = "아이디와 일치하는 음식 카테고리가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping("/join")
    public ApiResponse<UserResponse.JoinResultDto> join(@RequestBody @Valid UserRequest.JoinDto request){
        UserResponse.JoinResultDto response = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(response);
    }

    // 로그인
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    @PostMapping("/login")
    public ApiResponse<UserResponse.LoginResultDto> login(@RequestBody @Valid UserRequest.LoginRequestDto request,
                                                          HttpServletResponse response) {
        return ApiResponse.onSuccess(userCommandService.loginUser(request, response));
    }

    // 카카오 로그인 API
    @Operation(summary = "카카오 로그인 API",description = "카카오 소셜 로그인 API입니다.")
    @PostMapping("/login/kakao")
    public ApiResponse<UserResponse.LoginResultDto> kakaoLogin(@RequestParam("code") String accessCodet,
                                                          HttpServletResponse response) {
        return ApiResponse.onSuccess(oAuthService.kakaoOAuthLogin(accessCodet, response));
    }
}
