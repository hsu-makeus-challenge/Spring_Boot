package umc.spring.global.common.apiPayload.exception.handler;

import umc.spring.global.common.apiPayload.code.BaseErrorCode;
import umc.spring.global.common.apiPayload.exception.GeneralException;

public class ErrorHandler extends GeneralException {

    public ErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
