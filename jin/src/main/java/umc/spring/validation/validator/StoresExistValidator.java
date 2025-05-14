package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.service.StoreService.StoreService;
import umc.spring.validation.annotation.ExistStores;

@Component
@RequiredArgsConstructor
public class StoresExistValidator implements ConstraintValidator<ExistStores, Long> {

    private final StoreService storeService;

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        // 값이 null일 경우 false → 검증 실패
        if (storeId == null) {
            return false;
        }

        // storeService를 통해 존재 여부 확인
        return storeService.existsById(storeId);
    }
}