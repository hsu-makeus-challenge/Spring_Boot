package umc.spring.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserResponse {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MyPageDto {
        String nickName;
        String email;
        Boolean isPhoneVerified;
        Integer point;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "회원가입 응답 정보")
    public static class JoinResultDTO{
        @Schema(description = "회원가입이 완료된 유저 아이디", example = "1")
        Long userId;
        @Schema(description = "유저 가입 날짜")
        LocalDateTime createdAt;
    }
}
