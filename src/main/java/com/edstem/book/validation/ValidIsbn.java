package com.edstem.book.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsbnValidator.class)
@Documented
public @interface ValidIsbn {

    String message() default "Isbn provided is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
