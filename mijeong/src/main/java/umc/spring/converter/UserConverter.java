package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.UserPoint;
import umc.spring.web.dto.user.UserResponse;

public class UserConverter {

    public static UserResponse.MyPageDto toMypPageDto(User user, UserPoint userPoint) {
        return UserResponse.MyPageDto.builder()
                .build();
    }
}
