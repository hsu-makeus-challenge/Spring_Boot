package umc.spring.global.common;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import umc.spring.global.common.domain.TraceFileResource;

@Slf4j
@Aspect
@Component
public class ExceptionDiscordLoggingAspect {

  @Value("${discord.webhook.error-url}")
  private String webhookUrl;

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public ExceptionDiscordLoggingAspect(
      RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
    this.restTemplate = restTemplateBuilder.build();
    this.objectMapper = objectMapper;
  }

  private boolean shouldSkipNotification(Throwable ex) {
    SkipNotification skipNotification = ex.getClass().getAnnotation(SkipNotification.class);
    if (skipNotification == null) {
      return false;
    }
    return skipNotification.value();
  }

  @AfterThrowing(pointcut = "within(umc..*)", throwing = "ex")
  public void handleException(JoinPoint joinPoint, Throwable ex) {
    try {
      // log.info("Webhook URL: {}", webhookUrl);
      if(shouldSkipNotification(ex)){
        log.info("알림을 보내지 않음 : {}", ex.getClass().getName());
        return;
      }
      List<Map<String, Object>> fields = buildFields(joinPoint, ex);
      // 16711680 : 빨간색
      Map<String, Object> embed = Map.of("title", "서버 예외 발생", "color", 16711680, "fields", fields);
      Map<String, Object> payload = Map.of("embeds", List.of(embed));
      String fullStackTrace = buildStackTrace(ex);
      sendToDiscord(objectMapper.writeValueAsString(payload), fullStackTrace);
    } catch (Exception e) {
      log.warn("디스코드 예외 전송 실패: {}", e.getMessage());
    }
  }

  private List<Map<String, Object>> buildFields(JoinPoint joinPoint, Throwable ex) {
    List<Map<String, Object>> fields = new ArrayList<>();

    fields.add(Map.of("name", "Exception", "value", ex.getClass().getName(), "inline", false));
    fields.add(
        Map.of(
            "name",
            "Message",
            "value",
            ex.getMessage() != null ? ex.getMessage() : "No message",
            "inline",
            false));
    fields.add(
        Map.of(
            "name",
            "Location",
            "value",
            joinPoint.getSignature().toShortString(),
            "inline",
            false));
    fields.add(
        Map.of(
            "name",
            "Time",
            "value",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "inline",
            false));

    return fields;
  }

  private String buildStackTrace(Throwable ex) {
    StringBuilder sb = new StringBuilder();
    for (StackTraceElement element : ex.getStackTrace()) {
      sb.append(element.toString()).append("\n");
    }
    return sb.toString();
  }

  @Async
  protected void sendToDiscord(String payloadJson, String fullStackTrace) {
    try {
      byte[] traceBytes = fullStackTrace.getBytes(StandardCharsets.UTF_8);
      ByteArrayResource traceFile = new TraceFileResource(traceBytes, "stacktrace.txt");

      MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
      body.add("payload_json", payloadJson);
      body.add("file", traceFile);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.MULTIPART_FORM_DATA);

      HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

      ResponseEntity<String> response =
          restTemplate.postForEntity(webhookUrl, entity, String.class);
      log.info("디스코드 응답: {}", response.getStatusCode());

    } catch (Exception e) {
      log.error("디스코드 전송 실패: {}", e.getMessage(), e);
    }
  }
}
