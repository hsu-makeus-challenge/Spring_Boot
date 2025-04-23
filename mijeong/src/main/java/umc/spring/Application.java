package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserMission.UserMissionQueryService;

@EnableJpaAuditing
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "storeRunner")
	public CommandLineRunner storeRunner(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);
			String storeName = "요아정";
			Double storeRating = 4.0d;

			storeService.findStoresByNameAndScore(storeName, storeRating)
					.forEach(System.out::println);
			System.out.println("------------------------------");
		};
	}

	@Bean(name = "missionRunner")
	public CommandLineRunner missionRunner(ApplicationContext context) {
		return args -> {
			Long userId = 1L;
			MissionStatus status = MissionStatus.COMPLETED;
			Integer pageNumber = 1;

			UserMissionQueryService userMissionQueryService = context.getBean(UserMissionQueryService.class);
			Page<UserMission> userMissions = userMissionQueryService.findMissionsByStatus(userId, status, pageNumber);

			// 페이지 정보 출력
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
		};
	}
}
