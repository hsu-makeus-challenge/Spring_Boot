package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.converter.UserConverter;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Users;
import umc.study.domain.mapping.UserMission;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.service.UserService.UserCommandService;
import umc.study.service.UserService.UserMissionService;
import umc.study.service.UserService.UserQueryService;
import umc.study.validation.annotation.AlreadyJoinedMission;
import umc.study.web.dto.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
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
    public ApiResponse<UserMissionResponseDTO.UserMissionResultDto> joinMission(@PathVariable Integer userId, @PathVariable @AlreadyJoinedMission(userIdParameterName = "userId") Long missionId, @RequestBody @Valid UserMissionRequestDTO.UserMissionDto request){
        UserMission userMission = userMissionService.addMission(userId, missionId, request);
        return ApiResponse.onSuccess(UserMissionConverter.resultDTO(userMission));
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/{userId}/reviews")
    @Operation(summary = "사용자의 리뷰 목록 조회 API",description = "사용자의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable(name = "userId") Integer userId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = userQueryService.getReviewList(userId,page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    // 내가 진행중인 미션 목록 조회
    @GetMapping("/{userId}/missions")
    @Operation(summary = "사용자의 미션 목록 조회 API",description = "사용자의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissionList(@PathVariable(name = "userId") Integer userId, @RequestParam(name = "page") Integer page){
        // 방법 1. userId가 ?인 usermission을 페이징해서 id 값에따른 미션을 리스트로 바꾸고 또 그걸 페이징 해서 출력
        // 방법 2. jpql이나 queryDSL로 페이징
        Page<Mission> missionList = userMissionService.getMissionList(userId,page);
        return ApiResponse.onSuccess(MissionConverter.missionListDTO(missionList));
    }
}

