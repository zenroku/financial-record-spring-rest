package com.zenroku.financial.records.api.app.userwallet.constraint.uniqwalletperuser;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqWalletPerUserValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqWalletPerUser {
    String message() default "User and Wallet already existed";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
