package umc.spring.domain.mission.exception;

import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.exception.GeneralException;

public class MissionHandler extends GeneralException {

  public MissionHandler(BaseErrorCode code) {
    super(code);
  }
}
