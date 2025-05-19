package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.user.UserRequestDTO;
import umc.spring.web.dto.user.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request) {
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
        return User.builder()
                .address(request.getAddress())
                .gender(gender)
                .name(request.getName())
                .userPreferList(new ArrayList<>())
                .birth(request.getBirth())
                .isLocationAgreed(request.getIs_location_agreed())
                .isMarketingAgreed(request.getIs_marketing_agreed())
                .isPhoneVerified(request.getIs_phone_vertified())
                .isPrivateAgreed(request.getIs_private_agreed())
                .build();
    }
}
