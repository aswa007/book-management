package com.edstem.book.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitleValidator.class)
@Documented
public @interface ValidTitle {

    String message() default "Title should not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
