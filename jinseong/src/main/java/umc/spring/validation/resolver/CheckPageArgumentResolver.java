package umc.spring.validation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PageHandler;
import umc.spring.validation.annotation.CheckPage;

public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class) && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String pageParam = webRequest.getParameter("page");

        if (pageParam == null) {
            throw new PageHandler(ErrorStatus.PAGE_EMPTY);
        }

        int page = Integer.parseInt(pageParam);

        if (page < 1) {
            throw new PageHandler(ErrorStatus.PAGE_INVALID);
        }

        return page - 1;

    }
}
