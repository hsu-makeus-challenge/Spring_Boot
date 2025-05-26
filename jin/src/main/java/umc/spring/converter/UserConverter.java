package umc.spring.converter;

import lombok.extern.slf4j.Slf4j;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.user.UserRequestDTO;
import umc.spring.web.dto.user.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request) {
        if (request.getGender() == null) {
            log.error("gender 값이 null입니다.");
            throw new IllegalArgumentException("성별은 필수입니다.");
        }

        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> throw new IllegalArgumentException("올바르지 않은 성별 값입니다.");
        };

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .gender(gender)
                .role(request.getRole())
                .userPreferList(new ArrayList<>())
                .birth(request.getBirth())
                .isLocationAgreed(false)
                .isMarketingAgreed(false)
                .isPhoneVerified(false)
                .isPrivateAgreed(false)
                .isServiceAgreed(false)
                .isAlarmAgreed(false)
                .build();
    }
}
