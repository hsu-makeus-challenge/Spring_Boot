package umc.spring.domain.store.exception.status;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.apiPayload.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public enum StoreErrorStatus implements BaseErrorCode {
  STORE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "STORE400", "요청한 가게 ID가 유효하지 않습니다."),
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "해당 가게를 찾을 수 없습니다.");

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
