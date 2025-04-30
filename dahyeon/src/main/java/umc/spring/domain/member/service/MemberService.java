package umc.spring.domain.member.service;

import umc.spring.domain.member.dto.MemberResponseDto;

public interface MemberService {
  void deleteMemberAndAllRelatedData(Long memberId);

  MemberResponseDto.MyPageResponseDto getMyPage(Long memberId);
}
