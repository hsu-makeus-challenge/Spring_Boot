package umc.spring.domain.member.service;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.domain.member.web.dto.MemberResponseDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberResponseDTO.CompleteDto completeMission(Long memberMissionId, String confirmNumber);

}
