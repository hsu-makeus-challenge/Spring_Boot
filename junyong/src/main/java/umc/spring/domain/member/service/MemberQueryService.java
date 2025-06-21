package umc.spring.domain.member.service;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.enums.MissionStatus;

public interface MemberQueryService {
    MemberResponseDTO.MissionListDto getMissions(MissionStatus status, Integer page);
    MemberResponseDTO.MemberInfoDto getMemberInfo(HttpServletRequest request);
}
