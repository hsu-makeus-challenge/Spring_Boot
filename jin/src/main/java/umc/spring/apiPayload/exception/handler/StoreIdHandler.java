package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;

public class StoreIdHandler extends GeneralException {
    public StoreIdHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}