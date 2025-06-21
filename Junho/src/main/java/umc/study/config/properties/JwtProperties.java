package umc.study.config.properties;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("jwt.token")
public class JwtProperties {
    private String secretKey="";
    private Expiration expiration;

    @Getter
    @Setter
    public static class Expiration{
        private Long access;
        // TODO: refreshToken
    }


    // 진단용 로그 메서드
    @PostConstruct
    public void printProps() {
        System.out.println("=== JwtProperties 진단용 로그 ===");
        System.out.println("secretKey: " + secretKey);
        System.out.println("expiration: " + expiration);
        if (expiration != null) {
            System.out.println("access: " + expiration.getAccess());
        }
        System.out.println("===============================");
    }
}