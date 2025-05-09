package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.UserPoint;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    // 유저 생성
    public static User toUser(UserRequest.JoinDto request){
        return User.builder()
                .email(request.getEmail())
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

    public static UserResponse.JoinResultDTO toJoinResultDTO(User user){
        return UserResponse.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 전체 주소 포맷팅 메서드
    private static String formatFullAddress(String address, String addressDetail) {
        return String.format("%s %s", address, addressDetail);
    }
}
