package umc.spring.domain.member.exception.status;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.apiPayload.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public enum MemberErrorStatus implements BaseErrorCode {
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 사용자를 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  @Override
  public ErrorReasonDto getReason() {
    return ErrorReasonDto.builder().message(message).code(code).isSuccess(false).build();
  }

  @Override
  public ErrorReasonDto getReasonHttpStatus() {
    return ErrorReasonDto.builder()
        .message(message)
        .code(code)
        .isSuccess(false)
        .httpStatus(httpStatus)
        .build();
  }
}
