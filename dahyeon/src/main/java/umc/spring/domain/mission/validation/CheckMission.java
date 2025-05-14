package umc.spring.domain.mission.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CheckMissionValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface CheckMission {

    String message() default "도전 중인 미션 검증 실패";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

