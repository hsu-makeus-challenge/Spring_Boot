package umc.spring.apiPayload.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import umc.spring.config.DiscordFeignConfig;
import umc.spring.web.dto.DiscordMessage;

@FeignClient(
        name = "discord-client",
        url = "${discord.web-hook-url}",
        configuration = DiscordFeignConfig.class)
public interface DiscordClient {

    @PostMapping()
    void sendAlarm(@RequestBody DiscordMessage message);
}