package umc.spring.domain.mission.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.domain.mission.validation.validator.MemberMissionExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberMissionExistValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMemberMission {
    String message() default "오류야 오류";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
