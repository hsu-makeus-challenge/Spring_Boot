package umc.spring.domain.mission.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.member.service.MemberMissionQueryService;
import umc.spring.domain.mission.data.enums.MissionStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/")
    public void findMemberMissions(){
        // 수동 컨트롤러...^^
        MissionStatus status = MissionStatus.COMPLETE;
        int offset = 0;
        int limit = 5;
        memberMissionQueryService.findMemberMissions(status, offset, limit);
    }

}
