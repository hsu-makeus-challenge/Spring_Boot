package umc.spring.domain.mission.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.domain.mission.validation.validator.MemberMissionExistByMissionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberMissionExistByMissionValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberMissionExistByMission {
    String message() default "이미 도전중인 미션입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
