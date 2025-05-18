package umc.spring.domain.member.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.service.MemberCommandService;
import umc.spring.domain.member.service.MemberQueryService;
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.global.common.apiPayload.ApiResponse;
import umc.spring.global.common.validation.annotation.PageValid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

    @GetMapping("/missions")
    @Operation(summary = "내가 도전중인 미션 목록 조회하기 API", description = "내가 도전 중/도전 완료한 미션을 조회합니다. page와 status가 필요합니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "status", description = "미션의 상태(도전중/성공/실패) Query String"),
            @Parameter(name = "page", description = "페이지 번호. 1 이상. Query String")
    })
    public ApiResponse<MemberResponseDTO.MissionListDto> missions(MissionStatus status, @PageValid Integer page) {
        MemberResponseDTO.MissionListDto missions = memberQueryService.getMissions(status, page);
        return ApiResponse.onSuccess(missions);
    }

}
