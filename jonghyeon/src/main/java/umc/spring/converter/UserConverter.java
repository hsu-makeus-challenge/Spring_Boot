package umc.spring.converter;

import umc.spring.domain.Preference;
import umc.spring.domain.Region;
import umc.spring.domain.User;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static User toNewUser(UserRequestDTO.JoinDto request, Region region, String regionDetail, List<Preference> preferenceList) {
        User.Gender gender = switch (request.getGender()) {
            case 1 -> User.Gender.M;
            case 2 -> User.Gender.F;
            case 3 -> User.Gender.Other;
            default -> null;
        };

        LocalDate birthDate = LocalDate.of(
                request.getBirthYear(),
                request.getBirthMonth(),
                request.getBirthDay()
        );
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .gender(gender)
                .region(region)
                .regionDetail(regionDetail)
                .birthDate(birthDate)
                .role(request.getRole())
                .marketingConsent(request.getMarketingConsent())
                .LocationConsent(request.getLocationConsent())
                .newEventAlarm(request.getNewEventAlarm())
                .reviewRelyAlarm(request.getReviewRelyAlarm())
                .inquriyRelyAlarm(request.getInquriyRelyAlarm())
                .preferenceList(preferenceList)
                .missionRecordList(new ArrayList<>())
                .pointRecordList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .alarmList(new ArrayList<>())
                .build();

        preferenceList.forEach(pref -> pref.setUser(user));
        user.setPreferenceList(preferenceList); // 양방향
        return user;
    }

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static UserResponseDTO.LoginResultDTO toLoginResultDTO(Long id, String accessToken) {

        return UserResponseDTO.LoginResultDTO.builder()
                .UserId(id)
                .accessToken(accessToken)
                .build();

    }

        public static UserResponseDTO.UserInfoDTO toUserInfoDTO(User user){

            String gender = switch (user.getGender()) {
                case M -> "남성";
                case F -> "여성";
                case Other -> "기타";
                default -> null;
            };

        return UserResponseDTO.UserInfoDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .gender(gender)
                .build();

        }
}