package umc.spring.global.common.wehook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@Service
@Slf4j
public class DiscordWebhookService {

    @Value("${discord.webhook-url}")
    String discordWebhookUrl;

    public void sendDiscordWebhookMessage(DiscordWebhookMessage message) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json; utf-8");
            HttpEntity<DiscordWebhookMessage> messageEntity = new HttpEntity<>(message, httpHeaders);

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> response = template.exchange(
                    discordWebhookUrl,
                    POST,
                    messageEntity,
                    String.class
            );

            if (response.getStatusCode().value() != NO_CONTENT.value()) {
                log.error("메시지 전송 이후 에러 발생");
            }

        } catch (Exception e) {
            log.error("에러 발생 :: " + e);
        }
    }
}
