package umc.spring.domain.member.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class LoginDto {

    @Getter
    @Setter
    public static class LoginRequestDto{
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다")
        private String password;


    }

    @Getter
    @Builder
    public static class LoginResultDto{
        Long memberId;
        String accessToken;
    }

}
