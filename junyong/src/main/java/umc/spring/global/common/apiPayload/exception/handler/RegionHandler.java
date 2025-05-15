package umc.spring.global.common.apiPayload.exception.handler;

import umc.spring.global.common.apiPayload.code.BaseErrorCode;
import umc.spring.global.common.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode code) {
        super(code);
    }
}
