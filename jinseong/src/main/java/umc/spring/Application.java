package umc.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.UserService.UserCommandService;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	private final UserCommandService userCommandService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// UserService 호출
		Long userId = 1L;
		userCommandService.deleteUserWithRelatedEntities(userId);
		System.out.println("삭제 완료");
	}
}
