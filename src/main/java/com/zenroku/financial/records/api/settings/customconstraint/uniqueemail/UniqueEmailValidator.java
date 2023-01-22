package com.zenroku.financial.records.api.settings.customconstraint.uniqueemail;

import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return userRepository.findByEmail(s) == null;
    }
}
