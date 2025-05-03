package umc.spring.global.exception;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import umc.spring.global.apiPayload.ApiResponse;
import umc.spring.global.apiPayload.code.ErrorReasonDto;
import umc.spring.global.apiPayload.code.status.ErrorStatus;

/** 전역 예외 처리 클래스 모든 RestController에서 발생하는 예외를 처리한다. */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

  /**
   * Bean Validation API의 제약 조건 위반(@NotNull, @Min 등)에 대한 예외를 처리한다. 제약 조건 위반 시 발생하는
   * ConstraintViolationException을 처리한다.
   */
  @ExceptionHandler
  public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
    // 제약조건 위반 중 첫 번째 메시지만 추출
    String errorMessage =
        e.getConstraintViolations().stream()
            .map(constraintViolation -> constraintViolation.getMessage())
            .findFirst()
            .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

    return handleExceptionInternalConstraint(
        e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY, request);
  }

  /**
   * @Valid 어노테이션으로 검증 실패 시 발생하는 예외를 처리한다. ResponseEntityExceptionHandler의 메서드를 오버라이드하여 처리한다.
   */
  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    Map<String, String> errors = new LinkedHashMap<>();

    // 각 필드 에러에 대해 메시지를 수집함
    e.getBindingResult().getFieldErrors().stream()
        .forEach(
            fieldError -> {
              String fieldName = fieldError.getField();
              String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
              // 동일한 필드에 대한 에러 메시지는 쉼표로 구분하여 누적함
              errors.merge(
                  fieldName,
                  errorMessage,
                  (existingErrorMessage, newErrorMessage) ->
                      existingErrorMessage + ", " + newErrorMessage);
            });

    return handleExceptionInternalArgs(
        e, HttpHeaders.EMPTY, ErrorStatus.valueOf("_BAD_REQUEST"), request, errors);
  }

  /** 위의 핸들러들에서 처리되지 않은 모든 예외를 처리한다. 일반적인 서버 에러로 처리된다. */
  @ExceptionHandler
  public ResponseEntity<Object> exception(Exception e, WebRequest request) {
    e.printStackTrace();

    return handleExceptionInternalFalse(
        e,
        ErrorStatus._INTERNAL_SERVER_ERROR,
        HttpHeaders.EMPTY,
        ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(),
        request,
        e.getMessage());
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    String message =
        String.format(
            "지원하지 않는 HTTP 메서드 입니다.'%s'는 사용할 수 없으며, 다음 메서드만 허용됩니다: %s",
            ex.getMethod(), Arrays.toString(ex.getSupportedMethods()));

    //    return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    return handleExceptionInternalFalse(
        ex, ErrorStatus._BAD_REQUEST, headers, HttpStatus.METHOD_NOT_ALLOWED, request, message);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    String message =
        String.format(
            "지원하지 않는 미디어 타입입니다. '%s'는 사용할 수 없으며, 다음 타입만 허용됩니다: %s",
            ex.getContentType(), ex.getSupportedMediaTypes());

    //    return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    return handleExceptionInternalFalse(
        ex, ErrorStatus._BAD_REQUEST, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, message);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    String message = String.format("필수 경로 변수가 누락되었습니다. '%s' 변수는 필수 입니다.", ex.getVariableName());
    //    return super.handleMissingPathVariable(ex, headers, status, request);
    return handleExceptionInternalFalse(
        ex, ErrorStatus._BAD_REQUEST, headers, HttpStatus.BAD_REQUEST, request, message);
  }

  //
  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    String message = String.format("필수 파라미터가 누락되었습니다. '%s' 파라미터는 필수입니다.", ex.getParameterName());

    return handleExceptionInternalFalse(
        ex, ErrorStatus._BAD_REQUEST, headers, HttpStatus.BAD_REQUEST, request, message);
    //    return super.handleMissingServletRequestParameter(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    String message =
        String.format("필수 request part가 누락되었습니다. '%s' 부분은 필수입니다.", ex.getRequestPartName());

    return handleExceptionInternalFalse(
        ex, ErrorStatus._BAD_REQUEST, headers, HttpStatus.BAD_REQUEST, request, message);
    //    return super.handleMissingServletRequestPart(ex, headers, status, request);
  }

  // 내부 응답 생성 메서드들 (Internal Handlers)
  /** 비즈니스 로직에서 발생시키는 커스텀 예외를 처리한다 GeneralException은 비즈니스 예외를 담는 최상위 커스텀 예외 클래스임 */
  @ExceptionHandler(value = GeneralException.class)
  public ResponseEntity onThrowException(
      GeneralException generalException, HttpServletRequest request) {
    ErrorReasonDto errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();
    return handleExceptionInternal(generalException, errorReasonHttpStatus, null, request);
  }

  /** 예외 응답 생성을 위한 내부 메서드 ErrorReasonDTO를 사용하여 에러 응답을 생성한다 */
  private ResponseEntity<Object> handleExceptionInternal(
      Exception e, ErrorReasonDto reason, HttpHeaders headers, HttpServletRequest request) {
    ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);

    WebRequest webRequest = new ServletWebRequest(request);
    return super.handleExceptionInternal(e, body, headers, reason.getHttpStatus(), webRequest);
  }

  /** 실패 응답 생성을 위한 내부 메서드 에러 포인트(메시지)를 포함한 응답을 생성한다. */
  private ResponseEntity<Object> handleExceptionInternalFalse(
      Exception e,
      ErrorStatus errorCommonStatus,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request,
      String errorPoint) {
    ApiResponse<Object> body =
        ApiResponse.onFailure(
            errorCommonStatus.getCode(), errorCommonStatus.getMessage(), errorPoint);
    return super.handleExceptionInternal(e, body, headers, status, request);
  }

  /** 추가 인자를 포함한 예외 응답 생성을 위한 내부 메서드 필드 에러 등 추가적인 에러 정보를 포함한 응답을 생성한다. */
  private ResponseEntity<Object> handleExceptionInternalArgs(
      Exception e,
      HttpHeaders headers,
      ErrorStatus errorCommonStatus,
      WebRequest request,
      Map<String, String> errorArgs) {
    ApiResponse<Object> body =
        ApiResponse.onFailure(
            errorCommonStatus.getCode(), errorCommonStatus.getMessage(), errorArgs);
    return super.handleExceptionInternal(
        e, body, headers, errorCommonStatus.getHttpStatus(), request);
  }

  /** 제약조건 위반 응답 생성을 위한 내부 메서드 Bean Validation 제약조건 위반에 대한 응답을 생성합한다. */
  private ResponseEntity<Object> handleExceptionInternalConstraint(
      Exception e, ErrorStatus errorCommonStatus, HttpHeaders headers, WebRequest request) {
    ApiResponse<Object> body =
        ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
    return super.handleExceptionInternal(
        e, body, headers, errorCommonStatus.getHttpStatus(), request);
  }
}
