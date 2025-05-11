package umc.spring.web.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDto{
        @NotBlank
        String name;

        @NotBlank
        String nickname;

        @NotNull
        Integer gender;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 5, max = 12)
        String detailAddress;

        @NotNull
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        @ExistCategories
        List<Long> preferCategory;
    }
}
