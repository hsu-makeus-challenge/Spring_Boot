package umc.spring.global.apiPayload.code;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ErrorReasonDto {
    private HttpStatus httpStatus;

    private final boolean isSuccess;
    private final String code;
    private final String message;
    public boolean getIsSuccess() {
        return isSuccess;
    }

}
