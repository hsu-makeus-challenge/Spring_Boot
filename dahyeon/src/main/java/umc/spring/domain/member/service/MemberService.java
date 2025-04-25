package umc.spring.domain.member.service;

public interface MemberService {
  void deleteMemberAndAllRelatedData(Long memberId);
}
