package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import umc.spring.service.StoreService.StoreQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}



//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			// 파라미터 값 설정
//			String name = "요아정";
//			Float score = 4.0f;
//
//			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//		};
//	}
//
//	INSERT INTO mission (store_id, reward, content, deadline, created_at, updated_at)
//	VALUES
//			(1, 1000, '미션 1', '2025-06-01 23:59:59', NOW(), NOW()),
//			(1, 2000, '미션 2', '2025-06-02 23:59:59', NOW(), NOW()),
//			(1, 1500, '미션 3', '2025-06-03 23:59:59', NOW(), NOW()),
//			(1, 1800, '미션 4', '2025-06-04 23:59:59', NOW(), NOW()),
//			(1, 1300, '미션 5', '2025-06-05 23:59:59', NOW(), NOW()),
//			(1, 1700, '미션 6', '2025-06-06 23:59:59', NOW(), NOW()),
//			(1, 1600, '미션 7', '2025-06-07 23:59:59', NOW(), NOW()),
//			(1, 1400, '미션 8', '2025-06-08 23:59:59', NOW(), NOW()),
//			(1, 1900, '미션 9', '2025-06-09 23:59:59', NOW(), NOW()),
//			(1, 1200, '미션 10', '2025-06-10 23:59:59', NOW(), NOW()),
//			(1, 1250, '미션 11', '2025-06-11 23:59:59', NOW(), NOW()),
//			(1, 1550, '미션 12', '2025-06-12 23:59:59', NOW(), NOW()),
//			(1, 1650, '미션 13', '2025-06-13 23:59:59', NOW(), NOW()),
//			(1, 1750, '미션 14', '2025-06-14 23:59:59', NOW(), NOW()),
//			(1, 1350, '미션 15', '2025-06-15 23:59:59', NOW(), NOW()),
//			(1, 1450, '미션 16', '2025-06-16 23:59:59', NOW(), NOW()),
//			(1, 1950, '미션 17', '2025-06-17 23:59:59', NOW(), NOW()),
//			(1, 1150, '미션 18', '2025-06-18 23:59:59', NOW(), NOW()),
//			(1, 1100, '미션 19', '2025-06-19 23:59:59', NOW(), NOW()),
//			(1, 1050, '미션 20', '2025-06-20 23:59:59', NOW(), NOW());
//INSERT INTO review (content, score, store_id, user_id, created_at, updated_at)
//	VALUES
//			('맛있었어요 1', 4.5, 1, 1, NOW(), NOW()),
//			('맛있었어요 2', 4.0, 1, 1, NOW(), NOW()),
//			('맛있었어요 3', 5.0, 1, 1, NOW(), NOW()),
//			('맛있었어요 4', 3.5, 1, 1, NOW(), NOW()),
//			('맛있었어요 5', 4.8, 1, 1, NOW(), NOW()),
//			('맛있었어요 6', 3.7, 1, 1, NOW(), NOW()),
//			('맛있었어요 7', 5.0, 1, 1, NOW(), NOW()),
//			('맛있었어요 8', 4.2, 1, 1, NOW(), NOW()),
//			('맛있었어요 9', 3.9, 1, 1, NOW(), NOW()),
//			('맛있었어요 10', 4.6, 1, 1, NOW(), NOW()),
//			('맛있었어요 11', 4.4, 1, 1, NOW(), NOW()),
//			('맛있었어요 12', 4.1, 1, 1, NOW(), NOW()),
//			('맛있었어요 13', 4.3, 1, 1, NOW(), NOW()),
//			('맛있었어요 14', 4.0, 1, 1, NOW(), NOW()),
//			('맛있었어요 15', 3.8, 1, 1, NOW(), NOW()),
//			('맛있었어요 16', 4.9, 1, 1, NOW(), NOW()),
//			('맛있었어요 17', 5.0, 1, 1, NOW(), NOW()),
//			('맛있었어요 18', 4.6, 1, 1, NOW(), NOW()),
//			('맛있었어요 19', 4.2, 1, 1, NOW(), NOW()),
//			('맛있었어요 20', 3.6, 1, 1, NOW(), NOW());


}
