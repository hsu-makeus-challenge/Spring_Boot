    package umc.spring.web.dto.user;

    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import lombok.Getter;
    import lombok.Setter;
    import umc.spring.domain.enums.Role;
    import umc.spring.validation.annotation.ExistCategories;

    import java.time.LocalDate;
    import java.util.List;

    public class UserRequestDTO {

        @Getter
        @Setter // thymeleaf에서 사용하기 위해 추가
        public static class JoinDto{
            @NotBlank
            String name;
            @NotNull
            Integer gender;
            @NotNull
            LocalDate birth;
            @NotNull
            String address;
            @ExistCategories
            List<Long> preferCategory;
            @NotBlank
            @Email
            String email;
            @NotBlank
            String password;
            @NotNull
            Role role;
        }

        @Getter
        @Setter
        public static class LoginRequestDTO{
            @NotBlank(message = "이메일은 필수입니다")
            @Email(message = "올바른 이메일 형식이여야 합니다")
            private String email;

            @NotBlank(message = "패스워드는 필수입니다")
            private String password;
        }
    }
