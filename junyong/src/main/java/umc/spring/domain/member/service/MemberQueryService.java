package umc.spring.domain.member.service;

import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.enums.MissionStatus;

public interface MemberQueryService {
    MemberResponseDTO.MissionListDto getMissions(MissionStatus status, Integer page);
}
