package umc.spring.domain.member.service;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    public Member joinMember(MemberRequestDTO.JoinDto request);

}
