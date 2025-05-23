package umc.study.validation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.validation.annotation.PageParam;

public class PageParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageParam.class) &&
                parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        PageParam pageParam = parameter.getParameterAnnotation(PageParam.class);
        String pageStr = webRequest.getParameter("page");
        int page = pageStr != null ? Integer.parseInt(pageStr) : pageParam.defaultValue();

        if (page <= 0) {
            throw new GeneralException(ErrorStatus.PAGE_INVALID);  // 공통 예외로 처리
        }

        return page - 1; // 내부적으로 0부터 시작
    }
}
