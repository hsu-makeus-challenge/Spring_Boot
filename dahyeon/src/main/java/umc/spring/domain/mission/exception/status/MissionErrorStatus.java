package umc.spring.domain.mission.exception.status;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.apiPayload.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public enum MissionErrorStatus implements BaseErrorCode {
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 사용자를 찾을 수 없습니다."),
  MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "해당 미션을 찾을 수 없습니다."),
  MISSION_ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400", "이미 도전 중인 미션입니다."),
  MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION401", "이미 완료한 미션입니다."),
  MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION402", "현재 도전 중인 미션이 아닙니다."),
  MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION403", "기간이 만료된 미션입니다."),
  MISSION_ACCESS_DENIED(HttpStatus.FORBIDDEN, "MISSION403_FORBIDDEN", "이 미션에 접근할 권한이 없습니다."),
  INVALID_MISSION_REQUEST(HttpStatus.BAD_REQUEST, "MISSION405", "잘못된 미션 요청입니다.");

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
