package umc.spring.apiPayload.exception.handler;

import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {


    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
