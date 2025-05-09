package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserCommandService userCommandService;

    // 회원가입
    @Operation(
            summary = "회원가입",
            description = "회원가입 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "FOOD_CATEGORY4001", description = "존재하지 않는 음식 카테고리 입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping("/join")
    public ApiResponse<UserResponse.JoinResultDTO> join(@RequestBody @Valid UserRequest.JoinDto request){
        UserResponse.JoinResultDTO response = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(response);
    }
}
