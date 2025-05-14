package umc.spring.domain.member.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "MemberSignUpRequestDto", description = "회원가입 성공 시 반환되는 응답 DTO")
public class MemberSignUpResponseDto {
  @Schema(description = "회원 고유 ID", example = "1")
  Long memberId;

  @Schema(description = "회원가입 완료 시간", format = "date-time", example = "2025-05-12T14:30:00")
  LocalDateTime createdAt;
}
