package umc.spring.global.common.apiPayload.exception.handler;

import umc.spring.global.common.apiPayload.code.BaseErrorCode;
import umc.spring.global.common.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
