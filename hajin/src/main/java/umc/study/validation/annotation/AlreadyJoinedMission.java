package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.AlreadyJoinedMissionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyJoinedMissionValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyJoinedMission {
    String message() default "이미 해당 미션을 등록한 사용자입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String userIdParameterName(); // 사용자 ID 파라미터 이름
}