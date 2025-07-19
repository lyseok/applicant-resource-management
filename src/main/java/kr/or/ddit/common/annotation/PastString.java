package kr.or.ddit.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PastStringValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PastString {
    String message() default "과거 날짜만 입력 가능합니다. (yyyy-MM-dd 형식)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}