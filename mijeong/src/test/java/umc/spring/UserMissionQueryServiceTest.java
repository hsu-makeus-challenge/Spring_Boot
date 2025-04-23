package umc.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.UserMission.UserMissionQueryService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMissionQueryServiceTest {

    @Autowired
    private UserMissionQueryService userMissionQueryService;

    @Test
    public void testFindMissionsByStatus() {
        // given
        Long userId = 1L;
        MissionStatus status = MissionStatus.COMPLETED;
        Integer pageNumber = 1;

        // when
        Page<UserMission> userMissions = userMissionQueryService.findMissionsByStatus(userId, status, pageNumber);

        // then
        System.out.println("총 요소 수: " + userMissions.getTotalElements());
        System.out.println("총 페이지 수: " + userMissions.getTotalPages());
        System.out.println("현재 페이지 번호: " + userMissions.getNumber());
        System.out.println("페이지 당 항목 수: " + userMissions.getSize());
        System.out.println("현재 페이지 요소 수: " + userMissions.getNumberOfElements());
        System.out.println("첫 페이지 여부: " + userMissions.isFirst());
        System.out.println("마지막 페이지 여부: " + userMissions.isLast());

        userMissions.getContent().forEach(userMission -> {
            System.out.println("UserMission ID: " + userMission.getId());
            System.out.println("UserMission Status: " + userMission.getMissionStatus());
            System.out.println("가게 이름: " + userMission.getStoreMission().getStore().getStoreName());
            System.out.println("기준 금액: " + userMission.getStoreMission().getMission().getMissionMoney());
            System.out.println("지급 포인트: " + userMission.getStoreMission().getMission().getReward());
            System.out.println("------------------------------");
        });

        // 예시 검증
        assertThat(userMissions).isNotNull();
    }
}
