package umc.spring.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PageNumberHandler;
import umc.spring.validation.annotation.ValidPage;

public class ValidPageValidator implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String pageParam = webRequest.getParameter("page");

        //null이거나 비어있을 경우
        if (pageParam == null || pageParam.isEmpty()) {
            throw new PageNumberHandler(ErrorStatus.PAGE_NOT_FOUND);
        }

        // 0보다 작을 경우
        int page = Integer.parseInt(pageParam);
        if (page <= 0) {
            throw new PageNumberHandler(ErrorStatus.INVALID_PAGE_NUMBER);
        }
        return page - 1;  // 프론트 1 → 백엔드 0
    }
}
