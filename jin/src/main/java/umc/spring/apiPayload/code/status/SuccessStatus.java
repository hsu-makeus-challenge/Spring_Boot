package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseCode;
import umc.spring.apiPayload.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // ✅ 공통적으로 성공했을 때 사용할 enum 상수 (이름 앞에 "_" 붙이는 건 관례일 뿐)
    _OK(HttpStatus.OK, "COMMON200", "성공입니다.");

    // ✅ 각 성공 상태가 가질 정보들 (생성자에서 초기화됨)
    private final HttpStatus httpStatus; // HTTP 상태 코드 (예: 200 OK)
    private final String code;           // 커스텀 성공 코드 (프론트와 명확한 통신용)
    private final String message;        // 사용자에게 보여줄 메시지

    @Override
    public ReasonDTO getReason() {
        // ✅ HTTP 상태 없이 기본 성공 정보만 담은 DTO 반환
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true) // 성공 응답임을 명시
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        // ✅ HTTP 상태 포함한 전체 성공 정보 DTO 반환
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
