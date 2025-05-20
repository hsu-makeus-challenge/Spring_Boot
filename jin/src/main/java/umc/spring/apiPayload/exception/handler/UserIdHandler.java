package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;

public class UserIdHandler extends GeneralException {
    public UserIdHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}