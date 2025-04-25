package umc.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan(basePackages = "umc.study.domain")
@EnableJpaAuditing // 데이터베이스 엔티티의 생성과 수정 정보 자동 기록, 관리
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
