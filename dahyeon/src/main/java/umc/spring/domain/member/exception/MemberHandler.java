package umc.spring.domain.member.exception;

import umc.spring.global.apiPayload.code.BaseErrorCode;
import umc.spring.global.exception.GeneralException;

public class MemberHandler extends GeneralException {

  public MemberHandler(BaseErrorCode code) {
    super(code);
  }
}
