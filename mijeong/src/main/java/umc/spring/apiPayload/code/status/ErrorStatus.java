package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 유저 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "아이디와 일치하는 사용자가 없습니다."),

    // 404
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4031", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // 음식 카테고리 관련
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOOD_CATEGORY4001", "아이디와 일치하는 음식 카테고리가 없습니다."),

    // 가게 관련
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "아이디와 일치하는 가게가 없습니다."),

    // 지역 관련
    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4001", "아이디와 일치하는 지역이 없습니다."),

    // 미션 관련
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "아이디와 일치하는 미션이 없습니다."),

    // 가게 미션 관련
    STORE_MISSION_EXIST(HttpStatus.BAD_REQUEST, "STORE_MISSION4002", "이미 존재하는 가개 미션 입니다.");

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
}