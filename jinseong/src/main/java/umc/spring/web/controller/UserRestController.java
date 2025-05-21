package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.web.dto.UserDTO.UserRequestDTO;
import umc.spring.web.dto.UserDTO.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(
            @RequestBody @Valid UserRequestDTO.JoinDto request) {
        User user = userCommandService.joinMember(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/me/reviews")
    @Operation(summary = "나의 리뷰 목록 조회 API", description = "나의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 비어있습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4002", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "page", description = "1 이상의 페이지 번호를 입력하세요", required = true, in = ParameterIn.QUERY, example = "1")
    })
    public ApiResponse<UserResponseDTO.UserReviewPreViewListDTO> getReviewList(@CheckPage Integer page) {

        // 하드코딩해서 사용
        Long userId = 1L;

        Page<Review> reviewList = userQueryService.getReviewList(userId, page);

        return ApiResponse.onSuccess(UserConverter.userReviewPreViewListDTO(reviewList));
    }

    @GetMapping("/me/missions")
    @Operation(summary = "나의 미션 목록 조회 API", description = "나의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호가 비어있습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4002", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "page", description = "1 이상의 페이지 번호를 입력하세요", required = true, in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "status", description = "미션 상태를 입력하세요 (INPROGRESS / COMPLETE)", required = true, in = ParameterIn.QUERY, example = "INPROGRESS"),
    })
    public ApiResponse<UserResponseDTO.UserMissionPreViewListDTO> getMissionList(@CheckPage Integer page, @Parameter UserMissionStatus status) {

        // 하드코딩해서 사용
        Long userId = 1L;

        Page<UserMission> userMissionList = userQueryService.getMissionList(userId, status, page);

        return ApiResponse.onSuccess(UserConverter.userMissionPreViewListDTO(userMissionList));
    }
}
