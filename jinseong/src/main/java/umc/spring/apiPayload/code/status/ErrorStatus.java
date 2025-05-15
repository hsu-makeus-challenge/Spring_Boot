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
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // 테스트
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // 음식 카테고리 관련
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FC4001", "음식 카테고리가 없습니다."),

    // 지역 관련
    NEIGHBORHOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "NEIGH4001", "지역이 없습니다."),

    // 가게 관련
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "가게가 없습니다."),

    // 미션 관련
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션이 없습니다."),
    STORE_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "SM4001", "해당 가게 미션이 없습니다."),
    USER_MISSION_ALREADY_EXISTS(HttpStatus.CONFLICT, "UM4001", "이미 도전 중인 미션입니다."),

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
                .build()
                ;
    }
}