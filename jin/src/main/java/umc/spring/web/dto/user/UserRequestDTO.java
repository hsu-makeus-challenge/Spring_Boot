package umc.spring.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        LocalDate birth;
        @NotNull
        String address;
        @NotNull
        Boolean is_service_agreed;
        @NotNull
        Boolean is_private_agreed;
        @NotNull
        Boolean is_location_agreed;
        @NotNull
        Boolean is_marketing_agreed;
        @NotNull
        Boolean is_phone_vertified;
        @ExistCategories
        List<Long> preferCategory;
    }
}
