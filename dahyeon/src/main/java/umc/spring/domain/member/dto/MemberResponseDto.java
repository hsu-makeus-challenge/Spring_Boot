package umc.spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.member.entity.Member;

public class MemberResponseDto {

  @Getter
  @Builder
  public static class MyPageResponseDto {
    private String nickname;
    private String email;
    private Integer totalPoint;

    public static MyPageResponseDto from(Member member) {
      int totalPoints =
          member.getPointLogs().stream().mapToInt(pointLog -> pointLog.getPoint()).sum();

      return MyPageResponseDto.builder()
          .nickname(member.getNickname())
          .email(member.getEmail())
          .totalPoint(totalPoints)
          .build();
    }
  }
}
