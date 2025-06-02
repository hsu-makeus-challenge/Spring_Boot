package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}