package umc.spring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.apiPayload.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
  private BaseErrorCode code;

  public ErrorReasonDto getErrorReason() {
    return this.code.getReason();
  }

  public ErrorReasonDto getErrorReasonHttpStatus() {
    return this.code.getReasonHttpStatus();
  }
}
