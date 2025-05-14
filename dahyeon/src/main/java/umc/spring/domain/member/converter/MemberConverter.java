package umc.spring.domain.member.converter;

import umc.spring.domain.member.dto.MemberSignUpRequestDto;
import umc.spring.domain.member.dto.MemberSignUpResponseDto;
import umc.spring.domain.member.entity.Member;

public class MemberConverter {
  public static MemberSignUpResponseDto toSignUpResponseDto(Member member) {
    return MemberSignUpResponseDto.builder()
        .memberId(member.getId())
        .createdAt(member.getCreatedDate())
        .build();
  }

  public static Member toMember(MemberSignUpRequestDto request) {
    return Member.builder()
        .nickname(request.getNickname())
        .email(request.getEmail())
        .name(request.getName())
        .gender(request.getGender())
        .birth(request.getBirth())
        .addressDetail(request.getAddressDetail())
        .address(request.getAddress())
        .foodCategory(request.getFoodCategory())
        .build();
  }
}
