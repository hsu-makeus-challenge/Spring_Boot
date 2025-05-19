package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.MissionService.MissionByUserQueryService;
import umc.spring.service.ReviewService.ReviewCommandServiceImpl;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserService.UserCommandService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {

			// StoreQueryService 테스트
			StoreQueryService storeService = context.getBean(StoreQueryService.class);
			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;
			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);


			// MissionByUserService 테스트
			MissionByUserQueryService missionService = context.getBean(MissionByUserQueryService.class);
			Long userNo = 1L;
			Boolean isCompleted = true;

			System.out.println("\nExecuting findMissionByUserIsCompleted with parameters:");
			System.out.println("UserNo: " + userNo);
			System.out.println("isCompleted: " + isCompleted);

			// ReviewService 테스트 (리뷰 insert)
			ReviewCommandServiceImpl reviewCommandServiceImpl = context.getBean(ReviewCommandServiceImpl.class);
			Long storeId = 44L;
			Float rate = 4.9f;
			String content = "맛있어요! 자동 삽입 테스트 리뷰입니다.";

			System.out.println("\n▶ writeReview:");
			System.out.println("리뷰 저장 완료");

			// 4. UserService 테스트 (유저 조회)
			UserCommandService userCommandService = context.getBean(UserCommandService.class);
			Long userId = 1L;

			System.out.println("\n getUserInfo:");
			System.out.println("UserId: " + userId);

		};
	}

}
