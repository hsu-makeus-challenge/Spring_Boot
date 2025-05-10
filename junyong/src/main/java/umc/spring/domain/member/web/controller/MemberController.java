package umc.spring.domain.member.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.service.MemberCommandService;
import umc.spring.domain.member.service.MemberQueryService;
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.global.common.apiPayload.ApiResponse;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

}
