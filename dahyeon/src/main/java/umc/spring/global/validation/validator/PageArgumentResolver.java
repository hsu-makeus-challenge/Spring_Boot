package umc.spring.global.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;
import umc.spring.global.apiPayload.code.status.ErrorStatus;
import umc.spring.global.exception.handler.globalHandler;
import umc.spring.global.validation.annotation.PageCheck;

@Slf4j
@Component
public class PageArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(PageCheck.class);
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {

    String param = webRequest.getParameter(parameter.getParameterName());
    log.info(">>> PageArgumentResolver 실행됨. 값 = " + param);

    if (param == null) {
      throw new globalHandler(ErrorStatus.PAGE_IS_NULL);
    }
    int page;
    try {
      page = Integer.parseInt(param);
    } catch (NumberFormatException e) {
      throw new globalHandler(ErrorStatus.PAGE_FORMAT_INVALID);
    }

    if (page < 1) {
      throw new globalHandler(ErrorStatus.PAGE_MINIMUM);
    }

    return page - 1;
  }
}
