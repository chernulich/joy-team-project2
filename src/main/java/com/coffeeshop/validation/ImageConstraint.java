package com.coffeeshop.validation;

import com.coffeeshop.validation.validator.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface ImageConstraint {
    String message() default "Invalid image type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
