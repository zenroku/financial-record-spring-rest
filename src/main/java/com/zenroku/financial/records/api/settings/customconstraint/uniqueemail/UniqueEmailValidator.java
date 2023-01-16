package com.zenroku.financial.records.api.settings.customconstraint.uniqueemail;

import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.settings.customconstraint.uniqueemail.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(s) == null;
    }
}
