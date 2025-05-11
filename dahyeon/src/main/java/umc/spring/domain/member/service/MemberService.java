package umc.spring.domain.member.service;

import umc.spring.domain.member.dto.MemberResponseDto;
import umc.spring.domain.member.dto.MemberSignUpRequestDto;
import umc.spring.domain.member.dto.MemberSignUpResponseDto;
import umc.spring.domain.member.entity.Member;

public interface MemberService {
  void deleteMemberAndAllRelatedData(Long memberId);

  MemberResponseDto.MyPageResponseDto getMyPage(Long memberId);

  Member signup(MemberSignUpRequestDto request);
}
