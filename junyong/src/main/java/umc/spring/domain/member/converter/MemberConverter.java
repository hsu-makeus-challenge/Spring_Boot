package umc.spring.domain.member.converter;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.web.dto.MemberResponseDTO;

public class MemberConverter {

    public static MemberResponseDTO.MyPageDTO toMyPageDTO(Member member, boolean isCertified) {

        // 인증 결과에 따라 다르게 반환하도록 함
        String phone = isCertified ? member.getPhone() : "미인증";

        return MemberResponseDTO.MyPageDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(phone)
                .point(member.getPoint())
                .build();

    }

}
