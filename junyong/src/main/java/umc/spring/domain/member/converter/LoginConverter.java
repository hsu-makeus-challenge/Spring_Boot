package umc.spring.domain.member.converter;

import umc.spring.domain.member.web.dto.LoginDto;

public class LoginConverter {

    public static LoginDto.LoginResultDto toLoginResultDto(Long memberId, String accessToken) {
        return LoginDto.LoginResultDto.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .build();
    }

}
