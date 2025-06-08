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

    // 애플리케이션 시작 시 값 제대로 들어왔는지 확인
    @PostConstruct
    public void testInit() {
        System.out.println("jwt.secretKey = " + secretKey);
        System.out.println("jwt.expiration.access = " + (expiration != null ? expiration.getAccess() : "expiration is null"));
    }

}