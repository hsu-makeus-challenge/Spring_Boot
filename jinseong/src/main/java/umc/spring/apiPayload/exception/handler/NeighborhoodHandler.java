package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class NeighborhoodHandler extends GeneralException {

    public NeighborhoodHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
