package umc.spring.global.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import umc.spring.global.apiPayload.ApiResponse;
import umc.spring.global.apiPayload.code.status.ErrorStatus;

@DisplayName("ExceptionAdvice 단위 테스트")
public class ExceptionAdviceTest {
  private ExceptionAdvice exceptionAdvice;
  private WebRequest request;
  private HttpHeaders headers;

  @BeforeEach
  void setup() {
    exceptionAdvice = new ExceptionAdvice();
    request = mock(WebRequest.class);
    headers = new HttpHeaders();
  }

  @Nested
  @DisplayName("HTTP 메서드 예외 처리")
  class HttpMethodExceptiontest {
    @Test
    @DisplayName("지원하지 않는 HTTP 메서드 요청시")
    void handleHttpRequestMethodNotSupported() {
      // given
      String notSuppertedMethod = "POST";
      String[] supportedMethods = {"GET", "PUT"};
      HttpRequestMethodNotSupportedException ex =
          new HttpRequestMethodNotSupportedException(notSuppertedMethod, List.of(supportedMethods));
      String expectedErrorPoint =
          String.format(
              "지원하지 않는 HTTP 메서드 입니다.'%s'는 사용할 수 없으며, 다음 메서드만 허용됩니다: %s",
              notSuppertedMethod, Arrays.toString(supportedMethods));

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.handleHttpRequestMethodNotSupported(
              ex, headers, HttpStatus.METHOD_NOT_ALLOWED, request);

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED),
          () -> assertThat(body.getIsSuccess()).isEqualTo(false),
          () -> assertThat(body.getMessge()).isEqualTo(ErrorStatus._BAD_REQUEST.getMessage()),
          () -> assertThat((String) body.getResult()).isEqualTo(expectedErrorPoint));
    }

    @Test
    @DisplayName("지원하지 않는 미디어 타입 요청시 예외 처리")
    void handleHttpMediaTypeNotSupported() {
      // given
      MediaType notSupportedType = MediaType.TEXT_MARKDOWN;
      List<MediaType> supportedType = Arrays.asList(MediaType.APPLICATION_JSON);
      HttpMediaTypeNotSupportedException ex =
          new HttpMediaTypeNotSupportedException(notSupportedType, supportedType);

      String expectedErrorPoint =
          String.format(
              "지원하지 않는 미디어 타입입니다. '%s'는 사용할 수 없으며, 다음 타입만 허용됩니다: %s",
              notSupportedType, supportedType);

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.handleHttpMediaTypeNotSupported(
              ex, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE),
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getMessge()).isEqualTo(ErrorStatus._BAD_REQUEST.getMessage()),
          () -> assertThat((String) body.getResult()).isEqualTo(expectedErrorPoint));
    }
  }

  @Nested
  @DisplayName("경로 변수 예외 처리")
  class PathVariableTest {
    @Test
    @DisplayName("필수 경로 변수 누락시 예외 처리")
    void handleMissingPathVariable() {
      // given
      String pathVariableName = "testId";
      MethodParameter parameter = mock(MethodParameter.class);
      MissingPathVariableException ex =
          new MissingPathVariableException(pathVariableName, parameter);

      String expectedErrorPoint =
          String.format("필수 경로 변수가 누락되었습니다. '%s' 변수는 필수 입니다.", pathVariableName);

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.handleMissingPathVariable(ex, headers, HttpStatus.BAD_REQUEST, request);

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getMessge()).isEqualTo(ErrorStatus._BAD_REQUEST.getMessage()),
          () -> assertThat((String) body.getResult()).isEqualTo(expectedErrorPoint));
    }
  }

  @Nested
  @DisplayName("요청 파라미터 예외 처리")
  class RequestParameterTest {
    @Test
    @DisplayName("필수 요청 파라미터 누락시 예외 처리")
    void handleMissingServletRequestParameter() {
      // given
      String parameterName = "name";
      String parameterType = "String";
      MissingServletRequestParameterException ex =
          new MissingServletRequestParameterException(parameterName, parameterType);

      String expectedErrorPoint =
          String.format("필수 파라미터가 누락되었습니다. '%s' 파라미터는 필수입니다.", parameterName);

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.handleMissingServletRequestParameter(
              ex, headers, HttpStatus.BAD_REQUEST, request);

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getMessge()).isEqualTo(ErrorStatus._BAD_REQUEST.getMessage()),
          () -> assertThat((String) body.getResult()).isEqualTo(expectedErrorPoint));
    }

    @Test
    @DisplayName("필수 Multipart 요청 파트 누락시 예외 처리")
    void handleMissingServletRequestPart() {
      // given
      String requestPartName = "testRequestDto";
      MissingServletRequestPartException ex =
          new MissingServletRequestPartException(requestPartName);
      String expectedErrorPoint =
          String.format("필수 request part가 누락되었습니다. '%s' 부분은 필수입니다.", requestPartName);

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.handleMissingServletRequestPart(
              ex, headers, HttpStatus.BAD_REQUEST, request);

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getMessge()).isEqualTo(ErrorStatus._BAD_REQUEST.getMessage()),
          () -> assertThat((String) body.getResult()).isEqualTo(expectedErrorPoint));
    }
  }

  @Nested
  @DisplayName("유효성 검증 예외 처리")
  class ValidationTest {
    @Test
    @DisplayName("@Valid 검증 실패시 예외 처리")
    void handleMethodArgumentNotValid() {
      // given
      MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
      BindingResult bindingResult = mock(BindingResult.class);
      FieldError fieldError = new FieldError("objectName", "field", "error message");

      when(exception.getBindingResult()).thenReturn(bindingResult);
      when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.handleMethodArgumentNotValid(
              exception, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, mock(WebRequest.class));

      // then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      assertThat(body.getIsSuccess()).isFalse();

      @SuppressWarnings("unchecked")
      Map<String, String> errors = (Map<String, String>) body.getResult();
      assertThat(errors).containsEntry("field", "error message");
    }

    @Test
    @DisplayName("제약조건 위반시 예외 처리")
    void validation() {
      // given
      Set<ConstraintViolation<?>> violations = new HashSet<>();
      ConstraintViolation<?> violation = mock(ConstraintViolation.class);
      when(violation.getMessage()).thenReturn("_BAD_REQUEST");
      violations.add(violation);

      ConstraintViolationException exception = new ConstraintViolationException(violations);

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.validation(exception, mock(WebRequest.class));

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();

      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getCode()).isEqualTo("COMMON400"),
          () -> assertThat(body.getMessge()).isEqualTo("잘못된 요청입니다."));
    }
  }

  @Nested
  @DisplayName("비즈니스 예외 처리")
  class BusinessExceptionTest {
    @Test
    @DisplayName("GeneralException 처리")
    void onThrowException() {
      // given
      GeneralException generalException = new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
      HttpServletRequest request = mock(HttpServletRequest.class);

      // when
      ResponseEntity<Object> response = exceptionAdvice.onThrowException(generalException, request);

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();
      Assertions.assertAll(
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getResult()).isNull(),
          () ->
              assertThat(body.getMessge())
                  .isEqualTo(ErrorStatus._INTERNAL_SERVER_ERROR.getMessage()),
          () -> assertThat(body.getCode()).isEqualTo("COMMON500"));
    }
  }

  @Nested
  @DisplayName("기타 예외 처리")
  class OtherExceptionTest {
    @Test
    @DisplayName("처리되지 않은 예외 처리")
    void handleException() {
      // given
      String errorMessage = "처리되지 않은 예외 발생";
      Exception exception = new RuntimeException(errorMessage);

      // when
      ResponseEntity<Object> response =
          exceptionAdvice.exception(exception, mock(WebRequest.class));

      // then
      ApiResponse<?> body = (ApiResponse<?>) response.getBody();

      Assertions.assertAll(
          () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR),
          () -> assertThat(body.getIsSuccess()).isFalse(),
          () -> assertThat(body.getCode()).isEqualTo(ErrorStatus._INTERNAL_SERVER_ERROR.getCode()),
          () ->
              assertThat(body.getMessge())
                  .isEqualTo(ErrorStatus._INTERNAL_SERVER_ERROR.getMessage()),
          () -> assertThat(body.getResult()).isEqualTo(errorMessage));
    }
  }
}
