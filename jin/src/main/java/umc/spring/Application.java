package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.MissionService.MissionByUserQueryService;
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

			// 4. UserService 테스트 (유저 조회)
			UserCommandService userCommandService = context.getBean(UserCommandService.class);
			Long userId = 1L;

			System.out.println("\n getUserInfo:");
			System.out.println("UserId: " + userId);

		};
	}

}
