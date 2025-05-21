package umc.spring.global.exception.handler;

import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.exception.GeneralException;

public class globalHandler extends GeneralException {

  public globalHandler(BaseErrorCode code) {
    super(code);
  }
}
