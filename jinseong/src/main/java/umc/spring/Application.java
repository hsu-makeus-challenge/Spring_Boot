package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.UserService.UserQueryService;

@SpringBootApplication
@EnableJpaAuditing
//@RequiredArgsConstructor
public class Application {

//	private final UserCommandService userCommandService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

/*	@Override
	public void run(String... args) throws Exception {
		// UserService 호출
		Long userId = 1L;
		userCommandService.deleteUserWithRelatedEntities(userId);
		System.out.println("삭제 완료");
	}*/

/*	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);
		};
	}*/

	// 1번
/*	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			UserMissionQueryService umService = context.getBean(UserMissionQueryService.class);

			// 파라미터 값 설정
			Long userId = 1L;
			UserMissionStatus status = UserMissionStatus.COMPLETE;
			int page = 0;
			int size = 5;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findMissionByUserIdAndUserMissionStatus with parameters:");
			System.out.println("User ID: " + userId);
			System.out.println("Status: " + status);
			System.out.println("Page: " + page);
			System.out.println("Size: " + size);

			Page<UserMission> userMissions = umService.findMissionByUserIdAndUserMissionStatus(userId, status, page, size);

			System.out.println("현재 페이지: " + (userMissions.getNumber() + 1));
			System.out.println("전체 페이지 수: " + userMissions.getTotalPages());
			System.out.println("현재 페이지에 포함된 항목 수: " + userMissions.getNumberOfElements());
			System.out.println("전체 항목 수: " + userMissions.getTotalElements());
			userMissions.forEach(System.out::println);
		};
	}*/

	// 2번
/*	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			ReviewCommandService reviewCommandService = context.getBean(ReviewCommandService.class);

			// 파라미터 값 설정
			Long userId = 1L;
			Long storeId = 1L;
			ReviewRequestDTO.CreateReviewDTO createReviewDTO = ReviewRequestDTO.CreateReviewDTO.builder()
					.content("맛있엉")
					.score(BigDecimal.valueOf(4.0))
					.build()
					;


			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findMissionByUserIdAndUserMissionStatus with parameters:");
			System.out.println("User ID: " + userId);
			System.out.println("Store ID: " + storeId);

			reviewCommandService.insertReview(userId, storeId, createReviewDTO);
		};
	}*/

	// 3번
/*	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreMissionQueryService smService = context.getBean(StoreMissionQueryService.class);

			// 파라미터 값 설정
			Long neighborhoodId = 1L;
			Long userId = 1L;
			int page = 0;
			int size = 5;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoreMissionsByNeighborhoodAndUser with parameters:");
			System.out.println("Neighborhood ID: " + neighborhoodId);
			System.out.println("User ID: " + userId);
			System.out.println("Page: " + page);
			System.out.println("Size: " + size);

			Page<StoreMission> storeMissions = smService.findStoreMissionsByNeighborhoodAndUser(neighborhoodId, userId, page, size);

			System.out.println("현재 페이지: " + (storeMissions.getNumber() + 1));
			System.out.println("전체 페이지 수: " + storeMissions.getTotalPages());
			System.out.println("현재 페이지에 포함된 항목 수: " + storeMissions.getNumberOfElements());
			System.out.println("전체 항목 수: " + storeMissions.getTotalElements());

			storeMissions.forEach(System.out::println);
		};
	}*/

	// 4번
	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			UserQueryService userService = context.getBean(UserQueryService.class);

			// 파라미터 값 설정
			Long userId = 1L;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findUserInfoById with parameter:");
			System.out.println("User ID: " + userId);

			System.out.println(userService.findUserInfoById(userId));
		};
	}
}
