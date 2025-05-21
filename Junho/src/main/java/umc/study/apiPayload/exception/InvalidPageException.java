package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.status.ErrorStatus;

public class InvalidPageException extends RuntimeException {
    private final ErrorStatus errorStatus;
    public InvalidPageException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }
}
