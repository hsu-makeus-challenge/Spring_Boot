package umc.spring.global.common.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.global.common.apiPayload.code.BaseErrorCode;
import umc.spring.global.common.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청"),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증 필요"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청"),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자 없음"),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다"),

    // 음식 카테고리 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "존재하지 않는 음식 카테고리입니다."),

    // 가게 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "존재하지 않는 식당입니다"),
    STORE_REQUIRED(HttpStatus.BAD_REQUEST, "STORE4002", "가게 정보가 필요합니다"),

    // 지역 관련 에러
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4001", "존재하지 않는 지역입니다"),
    REGION_REQUIRED(HttpStatus.BAD_REQUEST, "REGION4002", "지역 정보가 필요합니다."),

    // 미션 관련 에러
    MISSION_BAD_DEADLINE(HttpStatus.BAD_REQUEST, "MISSION4002", "올바르지 않은 날짜 형식입니다 yyyy-mm-dd"),

    // EX
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글 없음"),

    // tmp
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트테스트")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

    // 일반적 응답

}
