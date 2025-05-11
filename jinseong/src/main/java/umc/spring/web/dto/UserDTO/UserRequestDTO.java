package umc.spring.web.dto.UserDTO;

import lombok.Getter;
import umc.spring.domain.enums.Gender;

import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        String address;
        String detailAddress;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;

        List<Long> preferCategory;
    }
}
