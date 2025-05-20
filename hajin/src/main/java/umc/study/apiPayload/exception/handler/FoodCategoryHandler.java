package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
