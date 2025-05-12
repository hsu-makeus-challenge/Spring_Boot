package umc.study.converter;

import umc.study.domain.Users;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.UserResponseDTO;
import umc.study.web.dto.UserRequestDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(Users user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId((long) user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Users toUser(UserRequestDTO.JoinDto request) {

        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Users.builder()
                .address(request.getAddress())
                .gender(gender)
                .age(request.getAge())
                .name(request.getName())
                .foodPreferenceList(new ArrayList<>())
                .email(request.getEmail())
                .birth(request.getBirth())
                .point(request.getPoint())
                .build();
    }
}
