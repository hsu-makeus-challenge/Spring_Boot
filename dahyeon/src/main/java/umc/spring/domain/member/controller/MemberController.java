package umc.spring.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.dto.MemberSignUpRequestDto;
import umc.spring.domain.member.dto.MemberSignUpResponseDto;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.service.MemberService;
import umc.spring.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원", description = "회원 관련 API")
public class MemberController {
  private final MemberService memberService;

  @DeleteMapping("/{memberId}")
  @Operation(summary = "회원 탈퇴", description = "회원 ID를 기준으로 회원 및 관련 데이터를 삭제합니다.")
  public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
    memberService.deleteMemberAndAllRelatedData(memberId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/signup")
  @Operation(summary = "회원가입", description = "일반 회원가입 요청을 처리합니다.")
  public ApiResponse<MemberSignUpResponseDto> signup(@RequestBody @Valid MemberSignUpRequestDto request) {
    Member newMember = memberService.signup(request);
    return ApiResponse.onSuccess(MemberConverter.toSignUpResponseDto(newMember));
  }
}
