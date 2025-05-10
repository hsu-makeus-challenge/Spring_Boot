package umc.spring.global.common.apiPayload.exception.handler;

import umc.spring.global.common.apiPayload.code.BaseErrorCode;
import umc.spring.global.common.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }

}
