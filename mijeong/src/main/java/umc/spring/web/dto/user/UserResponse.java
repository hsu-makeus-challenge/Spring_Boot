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
    public static class JoinResultDto {
        @Schema(description = "회원가입이 완료된 유저 아이디", example = "1")
        Long userId;
        @Schema(description = "유저 가입 날짜")
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "로그인 응답 정보")
    public static class LoginResultDto {
        @Schema(description = "로그인에 성공한 유저 아이디", example = "1")
        Long userId;

        // accessToken은 response header에
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "유저 조회 응답 정보")
    public static class UserInfoDto{
        String nickName;
        String email;
        String gender;
    }
}
