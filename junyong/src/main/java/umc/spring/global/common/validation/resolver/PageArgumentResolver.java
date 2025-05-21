package umc.spring.global.common.validation.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;
import umc.spring.global.common.validation.annotation.PageValid;

@Component
@Slf4j
@RequiredArgsConstructor
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Integer.class) &&
                parameter.hasParameterAnnotation(PageValid.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String pageParameter = webRequest.getParameter("page");

        if (pageParameter.isBlank()) { // 빈칸일 때 ex) " "
            throw new ErrorHandler(ErrorStatus.PAGE_NOT_FOUND);
        }

        log.info("PageArgumentResolver, 입력한 page = {}", pageParameter);

        int page;
        try {
            page = Integer.parseInt(pageParameter);
        }catch (NullPointerException e){ // 값이 없을 때
            throw new ErrorHandler(ErrorStatus.PAGE_NOT_FOUND);
        }catch (NumberFormatException e){ // 숫자가 아닐 떄
            throw new ErrorHandler(ErrorStatus.PAGE_NOT_NUMBER);
        }catch (Exception e){
            throw new ErrorHandler(ErrorStatus.PAGE_ERROR);
        }

        if (page < 1) {
            throw new ErrorHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }

        return page - 1;
    }
}
