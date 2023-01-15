package com.zenroku.financial.records.api.settings.custom_constraint.annotation;

import com.zenroku.financial.records.api.settings.custom_constraint.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueEmail {
    String message() default "Email address is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
