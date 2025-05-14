package umc.spring.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "MemberSignUpRequestDto", description = "새로운 사용자의 정보 저장을 위한 회원가입 요청 DTO")
public class MemberSignUpRequestDto {

  @NotBlank(message = "사용자의 닉네임은 필수 입력 항목입니다. 최대 10자까지 입력 가능")
  @Size(max = 10)
  @Schema(description = "닉네임", example = "테스트닉네임", maxLength = 10, required = true)
  private String nickname;

  @Email(message = "올바른 이메일 형식이 아닙니다.")
  @NotBlank(message = "이메일은 필수 입력 항목입니다.")
  @Schema(description = "이메일 주소", example = "test@example.com", required = true)
  private String email;

  @NotBlank(message = "이름은 필수 입력 항목입니다.")
  @Schema(description = "사용자 이름", example = "홍길동", required = true)
  private String name;

  @Schema(
      description = "성별",
      example = "FE",
      allowableValues = {"FE", "M", "NONE"})
  private Gender gender;

  @NotNull(message = "생년월일은 필수 입력 항목입니다.")
  @Schema(
      description = "생년월일",
      example = "1990-01-01",
      type = "string",
      format = "date",
      required = true)
  private LocalDate birth;

  @Schema(description = "주소 (선택)", example = "서울특별시 강남구 테헤란로 123")
  private String address;

  @Schema(description = "상세 주소 (선택)", example = "101동 202호")
  private String addressDetail;

  @Schema(description = "이벤트 알림 수신 여부 (기본값: false)", example = "false", defaultValue = "false")
  private Boolean eventNotify = false;

  @Schema(description = "리뷰 알림 수신 여부 (기본값: false)", example = "false", defaultValue = "false")
  private Boolean reviewNotify = false;

  @Schema(description = "문의/답변 알림 수신 여부 (기본값: false)", example = "false", defaultValue = "false")
  private Boolean qaNotify = false;

  @Schema(description = "선호 음식 카테고리 목록", example = "[\"KOREAN\", \"JAPANESE\"]")
  private List<FoodCategory> foodCategory;
}
