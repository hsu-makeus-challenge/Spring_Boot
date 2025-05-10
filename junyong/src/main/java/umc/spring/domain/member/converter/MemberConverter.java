package umc.spring.domain.member.converter;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.enums.Gender;
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.domain.member.web.dto.MemberResponseDTO;

import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDto toJoinResultDto(Member member) {
        return MemberResponseDTO.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {

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

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .eventNoticeList(new ArrayList<>())
                .inquiryNoticeList(new ArrayList<>())
                .reviewNoticeList(new ArrayList<>())
                .memberMissionList(new ArrayList<>())
                .memberPreferList(new ArrayList<>())
                .build();
    }


}
