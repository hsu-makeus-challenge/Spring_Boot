package umc.spring.global.common.apiPayload.exception.handler;

import umc.spring.global.common.apiPayload.code.BaseErrorCode;
import umc.spring.global.common.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
