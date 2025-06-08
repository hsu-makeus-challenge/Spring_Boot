package umc.study.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;
//import umc.study.domain.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public class UserRequestDTO {

    @Getter
    @Setter
    public static class JoinDto{
        String name;
        @NotBlank
        @Email
        String email;    // 이메일 필드 추가
        @NotBlank
        String password;    // 비밀번호 필드 추가
        @NotNull
        String gender; // 받을 때는 정수 형태로
//        @NotNull
//        Integer gender; // 받을 때는 정수 형태로
        LocalDateTime birth;
        Integer age;
        String address;
        Integer point;
        String phone_number;
        Boolean phone_certification;
        @NotNull
        Role role;    // 역할 필드 추가

        //@ExistCategories
        //List<Long> preferFood;
        // form에서 데이터가 문자 "1" "2"로 오기 때문에 String 타입으로 받음
        List<String> preferFood;
    }

    // token 기반 로그인 위한 dto
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
