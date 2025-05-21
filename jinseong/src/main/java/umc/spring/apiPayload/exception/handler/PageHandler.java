package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class PageHandler extends GeneralException {

    public PageHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
