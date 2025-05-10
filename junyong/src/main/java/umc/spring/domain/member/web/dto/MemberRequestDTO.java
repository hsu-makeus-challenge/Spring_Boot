package umc.spring.domain.member.web.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        String birth;
        String phone;
        String address;
        String specAddress;
        List<Long> preferCategory;
    }


}
