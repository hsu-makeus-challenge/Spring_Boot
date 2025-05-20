package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;

public class PageNumberHandler extends GeneralException {
    public PageNumberHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}