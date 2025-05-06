package umc.spring.global.exception;

import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import umc.spring.global.common.ExceptionDiscordLoggingAspect;
import umc.spring.global.mock.NotiException;

@Profile("test")
@ExtendWith(MockitoExtension.class)
public class ExceptionNotificationText {
  @InjectMocks private ExceptionDiscordLoggingAspect exceptionDiscordLoggingAspect;

  @Mock private JoinPoint joinPoint;

  @Mock private RestTemplate restTemplate;

  @Mock private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Nested
  @DisplayName("커스텀 예외 알림 처리")
  class SkipNotificationTest {
    @Test
    @DisplayName("NotiException 발생 시 알림 전송")
    void testNotiException_ShouldSendNotification() throws Exception {
      // given
      NotiException ex = new NotiException("error occurred");

      // when
      //      ResponseEntity<Object> response =
      //          exceptionDiscordLoggingAspect.handleException(joinPoint, ex);

      // then

    }
  }
}
