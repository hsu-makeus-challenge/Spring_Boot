package umc.spring.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("jwt.token") // jwt.token 하위 값을 이 클래스에 바인딩
public class JwtProperties {
    private String secretKey="";
    private Expiration expiration;

    @Getter
    @Setter
    public static class Expiration {
        private Long access; // 시간
    }
}
