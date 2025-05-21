package umc.spring.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PageNumberHandler;
import umc.spring.validation.annotation.ValidPage;


/**
 * 커스텀 어노테이션 @ValidPage가 붙은 page 파라미터를 검증하고,
 * 유효한 경우 0-based 인덱스로 변환하여 컨트롤러에 넘겨주는 Validator
 */
public class ValidPageValidator implements HandlerMethodArgumentResolver {

    /**
     * 해당 파라미터가 지원되는지 여부를 반환함.
     * 조건: @ValidPage 어노테이션이 붙어있고, 타입이 Integer인 경우
     */
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

        //쿼리 파라미터로부터 page값 가져오기
        String pageParam = webRequest.getParameter("page");

        //null이거나 비어있을 경우
        if (pageParam == null || pageParam.isEmpty()) {
            throw new PageNumberHandler(ErrorStatus.PAGE_NOT_FOUND);
        }

        // 0보다 작거나 같을 경우
        int page = Integer.parseInt(pageParam);
        if (page <= 0) {
            throw new PageNumberHandler(ErrorStatus.INVALID_PAGE_NUMBER);
        }
        return page - 1;  // 프론트 1 → 백엔드 0
    }
}
