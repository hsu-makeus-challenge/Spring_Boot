package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.Region;
import umc.spring.domain.User;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.TandF;

import java.util.Arrays;
import java.util.List;


public class UserRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {

        @NotBlank
        @Size(max = 20)
        private String name;

        @NotBlank
        @Email
        @Size(max = 50)
        private String email;

        @Pattern(regexp = "^\\d{10,20}$", message = "전화번호는 10~20자리 숫자여야 합니다.")
        private String phoneNumber;

        @NotNull
        @Schema(description = "성별 (M, F, Other 중 하나)")
        private Integer gender;

        @NotBlank
        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;

        @NotNull
        @Max(value = 9999, message = "출생 연도는 4자리 숫자여야 합니다.")
        @Min(value = 1900, message = "출생 연도는 1900년 이후여야 합니다.")
        private Integer birthYear;
        @NotNull
        @Max(value = 12, message = "출생 월은 1~12 사이의 숫자여야 합니다.")
        @Min(value = 1, message = "출생 월은 1~12 사이의 숫자여야 합니다.")
        private Integer birthMonth;
        @NotNull
        @Max(value = 31, message = "출생 일은 1~31 사이의 숫자여야 합니다.")
        @Min(value = 1, message = "출생 일은 1~31 사이의 숫자여야 합니다.")
        private Integer birthDay;

        private TandF marketingConsent = TandF.T;
        private TandF locationConsent = TandF.T;

        private TandF newEventAlarm = TandF.T;
        private TandF reviewRelyAlarm = TandF.T;
        private TandF inquriyRelyAlarm = TandF.T;

        private String address;
        private String specAddress;

        @NotNull
        private Role role;

        List<Long> preferCategory;


    }

    @Getter
    @Setter
    public static class LoginRequestDTO{
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}
