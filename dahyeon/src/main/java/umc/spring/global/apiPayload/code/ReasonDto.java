package umc.spring.global.apiPayload.code;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReasonDto {
  private HttpStatus httpStatus;

  private final boolean isSuccess;
  private final String code;
  private final String message;

  public boolean getIsSuccess() {
    return isSuccess;
  }
}
