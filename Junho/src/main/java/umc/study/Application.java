package umc.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.service.ReviewService.ReviewService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.service.UserMissionService.UserMissionQueryService;
import umc.study.service.UserService.UserQueryService;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            StoreQueryService storeService = ctx.getBean(StoreQueryService.class);
            UserQueryService userQueryService = ctx.getBean(UserQueryService.class);
            UserMissionQueryService userMissionQueryService = ctx.getBean(UserMissionQueryService.class);

            String name = "스타벅스 강남점";
            Float score = 4.0f;
            System.out.println("Executing findStoresByNameAndScore with parameter:");
            System.out.println("Name: " + name);
            System.out.println("Score: " + score);
            storeService.findStoresByNameAndScore(name, score).forEach(System.out::println);

            // 1번
            System.out.println("완료한 미션");
            userMissionQueryService.findClearedMissions().forEach(System.out::println);
            System.out.println("진행중인 미션");
            userMissionQueryService.findNotClearedMissions().forEach(System.out::println);

            //3번
            System.out.println("성공 미션 수, 포인트");
            System.out.println(userQueryService.getHomeInfo(Long.parseLong("1")));
            MissionQueryService missionQueryService = ctx.getBean(MissionQueryService.class);
            System.out.println("미션 목록");
            missionQueryService.showMissions().forEach(System.out::println);

            //4번
            System.out.println("사용자 1 정보 조회");
            System.out.println(userQueryService.getMyInfo(Long.parseLong("1")));
            System.out.println("사용자 2 정보 조회");
            System.out.println(userQueryService.getMyInfo(Long.parseLong("2")));
        };
    }

}
