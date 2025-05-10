package umc.spring.global.common.wehook;

public record DiscordWebhookMessage(String content) {

    public static DiscordWebhookMessage of(String content) {
        return new DiscordWebhookMessage(content);
    }
}
