package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.UserMissionExistValidator;

import java.lang.annotation.*;

// 유저 미션 존재 검증
@Documented
@Constraint(validatedBy = UserMissionExistValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUserMission {
    String message() default "아이디와 일치하는 유저 미션이 없습니다."; // 기본 에러 메시지

    Class<?>[] groups() default {}; // 유효성 검사 그룹

    Class<? extends Payload>[] payload() default {}; // 메타데이터 전달용
}