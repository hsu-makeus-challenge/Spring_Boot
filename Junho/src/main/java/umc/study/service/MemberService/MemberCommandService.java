package umc.study.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.domain.Member;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);

    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
