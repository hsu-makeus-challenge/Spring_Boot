package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistCategories;
//import umc.study.domain.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        String name;
        Integer gender; // 받을 때는 정수 형태로
        LocalDateTime birth;
        Integer age;
        String address;
        Integer point;
        String phone_number;
        Boolean phone_certification;
        String email;
        @ExistCategories
        List<Long> preferFood;
    }
}
