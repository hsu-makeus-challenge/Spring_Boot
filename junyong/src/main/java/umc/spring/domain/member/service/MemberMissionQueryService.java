package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.region.repository.RegionRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final RegionRepository regionRepository;

    public void findMemberMissions(MissionStatus missionStatus, int offset, int size) {

        List<MemberMission> memberMissionList = memberMissionRepository.findMemberMissonList(missionStatus, offset, size);
        memberMissionList.forEach(
                memberMission -> System.out.println("가게 이름 = " + memberMission.getMission().getStore().getName())
        );
    }

    public void findMemberMissionByRegion(Long regionId){

        // 지역 구하기
        Region region = regionRepository.findById(regionId).orElse(null);
        System.out.println("지역명 = " + region.getName());

        //1. 미션 수 구하기 위한 List
        List<MemberMission> memberMissionListForCount = memberMissionRepository.findMemberMissionByRegion(region, MissionStatus.COMPLETE);

        //2. 도전 가능한 미션 구하는 List
        List<MemberMission> memberMissionListForList = memberMissionRepository.findMemberMissionByRegion(region, MissionStatus.CHALLENGING);

        //3. 결과 출력
        System.out.println("count = " + memberMissionListForCount.size()); // count 출력
        memberMissionListForList.forEach( // 미션 목록 출력
                memberMission -> {
                    System.out.println("Mission Id = " + memberMission.getMission().getId());
                }
        );

    }


}
