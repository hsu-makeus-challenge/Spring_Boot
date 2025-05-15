package umc.spring.domain.region.validation.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.region.service.RegionQueryServiceImpl;
import umc.spring.domain.region.validation.annotation.RegionEntity;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.RegionHandler;

@Component
@RequiredArgsConstructor
public class RegionArgumentResolver implements HandlerMethodArgumentResolver {

    private final RegionQueryServiceImpl regionQueryServiceImpl;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Region.class) &&
                parameter.hasParameterAnnotation(RegionEntity.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String regionCode = webRequest.getParameter("regionCode");

        // 1. null 검사
        if (regionCode == null) {
            throw new RegionHandler(ErrorStatus.REGION_REQUIRED);
        }
        // 2. 데이터 유무 검사
        return regionQueryServiceImpl.getRegionByRegionCode(regionCode)
                .orElseThrow( () -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
    }
}
