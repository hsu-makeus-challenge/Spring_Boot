package umc.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserMissionService.UserMissionQueryService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.web.dto.mission.MissionResponse;
import umc.spring.web.dto.user.UserResponse;


@EnableFeignClients(basePackages = "umc.spring")
@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
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
			Integer pageNumber = 0;

			UserMissionQueryService userMissionQueryService = context.getBean(UserMissionQueryService.class);
			Page<UserMission> userMissions = userMissionQueryService.findMissionsByStatus(userId, status, pageNumber);

			// 페이지 정보 출력
			log.info("총 요소 수: {}", userMissions.getTotalElements());
			log.info("총 페이지 수: {}", userMissions.getTotalPages());
			log.info("현재 페이지 번호: {}", userMissions.getNumber());
			log.info("페이지 당 항목 수: {}", userMissions.getSize());
			log.info("현재 페이지 요소 수: {}", userMissions.getNumberOfElements());
			log.info("첫 페이지 여부: {}", userMissions.isFirst());
			log.info("마지막 페이지 여부: {}", userMissions.isLast());

			userMissions.getContent().forEach(userMission -> {
				log.info("UserMission ID: {}", userMission.getId());
				log.info("UserMission Status: {}", userMission.getMissionStatus());
				log.info("가게 이름: {}", userMission.getStoreMission().getStore().getStoreName());
				log.info("기준 금액: {}", userMission.getStoreMission().getMission().getMissionMoney());
				log.info("지급 포인트: {}", userMission.getStoreMission().getMission().getReward());
				log.info("------------------------------");
			});
		};
	}

//	@Bean(name = "reviewRunner")
//	public CommandLineRunner reviewRunner(ApplicationContext context) {
//		return args -> {
//			ReviewRequest.ReviewCreateDto request = ReviewRequest.ReviewCreateDto.builder()
//					.usrId(1L)
//					.storeId(1L)
//					.reviewContent("너무 맛있어서 눈물나요")
//					.reviewRating(4.8)
//					.build();
//
//			ReviewCommandService reviewCommandService = context.getBean(ReviewCommandService.class);
//			reviewCommandService.saveReview(request);
//		};
//	}

	@Bean(name = "homeMissionRunner")
	public CommandLineRunner homeMissionRunner(ApplicationContext context, UserQueryService userQueryService) {
		return args -> {
			Long userId = 1L;
			Long regionId = 1L;
			MissionStatus status = MissionStatus.NOT_STARTED;
			Integer pageNumber = 0;

			UserMissionQueryService userMissionQueryService = context.getBean(UserMissionQueryService.class);
			MissionResponse.HomeMissionPageDto dto = userMissionQueryService.findNotStartedMissionsByRegion(userId, regionId, status, pageNumber);

			// 페이지 정보 출력
			log.info("총 요소 수: {}", dto.getTotalElements());
			log.info("총 페이지 수: {}", dto.getTotalPage());
			log.info("현재 페이지 요소 수: {}", dto.getMissionListSize());
			log.info("첫 페이지 여부: {}", dto.getIsFirst());
			log.info("마지막 페이지 여부: {}", dto.getIsLast());
			log.info("성공한 미션 개수: {}", dto.getCompleteMissionCount());

			dto.getMissionList().forEach(mission -> {
				log.info("UserMission ID: {}", mission.getMissionId());
				log.info("UserMission Status: {}", mission.getMissionStatus());
				log.info("가게 이름: {}", mission.getStoreName());
				log.info("음식 카테고리: {}", mission.getFoodCategoryList());
				log.info("기준 금액: {}", mission.getMissionMoney());
				log.info("지급 포인트: {}", mission.getReward());
				log.info("------------------------------");
			});
		};
	}

	@Bean(name = "myPageRunner")
	public CommandLineRunner myPageRunner(ApplicationContext context, UserQueryService userQueryService) {
		return args -> {
			UserResponse.MyPageDto user = userQueryService.getUserMyPage(1L);

			log.info("nickName: {}", user.getNickName());
			log.info("email: {}", user.getEmail());
			log.info("isPhoneVerified: {}", user.getIsPhoneVerified());
			log.info("point: {}", user.getPoint());
		};
	}
	 */
}
