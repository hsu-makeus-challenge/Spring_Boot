package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class StoreMissionHandler extends GeneralException {

    public StoreMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
