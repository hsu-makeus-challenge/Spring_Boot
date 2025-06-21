package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.UserPoint;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

import java.time.LocalDateTime;

public class UserConverter {

    // 유저 생성
    public static User toUser(UserRequest.JoinDto request){
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())   // 추가된 코드
                .role(request.getRole())   // 추가된 코드
                .nickName(request.getNickName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(formatFullAddress(request.getAddress(), request.getAddressDetail()))
                .phone(request.getPhone())
//                .userPreferList(new ArrayList<>())
                .build();
    }

    public static UserResponse.MyPageDto toMypPageDto(User user, UserPoint userPoint) {
        return UserResponse.MyPageDto.builder()
                .build();
    }

    public static UserResponse.JoinResultDto toJoinResultDTO(User user){
        return UserResponse.JoinResultDto.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 로그인 응답
    public static UserResponse.LoginResultDto toLoginResultDto(Long userId) {
        return UserResponse.LoginResultDto.builder().userId(userId).build();
    }

    // 유저 정보 응답
    public static UserResponse.UserInfoDto toUserInfoDto(User user) {
        return UserResponse.UserInfoDto.builder()
                .nickName(user.getNickName())
                .email(user.getEmail())
                .gender(user.getGender().toString())
                .build();
    }

    // 전체 주소 포맷팅 메서드
    private static String formatFullAddress(String address, String addressDetail) {
        return String.format("%s %s", address, addressDetail);
    }

    // 소셜 로그인용 테스트 유저 생성
    public static User toUser(String email, String nickName) {
        return User.builder()
                .email(email)
                .nickName(nickName)
                .role(Role.USER)
                .gender(Gender.NONE)
                .birth("2004/01/08")
                .address("test address")
                .phone("010-1111-1111")
                .build();
    }
}
