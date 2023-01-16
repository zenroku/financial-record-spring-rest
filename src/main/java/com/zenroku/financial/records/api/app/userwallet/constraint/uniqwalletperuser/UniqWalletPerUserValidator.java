package com.zenroku.financial.records.api.app.userwallet.constraint.uniqwalletperuser;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.userwallet.repository.UserWalletRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqWalletPerUserValidator implements ConstraintValidator<UniqWalletPerUser, UserWallet> {
    @Autowired
    UserWalletRepository userWalletRepository;
    @Override
    public boolean isValid(UserWallet userWallet, ConstraintValidatorContext constraintValidatorContext) {
        if (userWallet.getUserRelations() == null || userWallet.getWalletRelations() == null){
            return true;
        }

        if (userWallet.getId() != null){
            return userWalletRepository.findById(userWallet.getId()).isPresent();
        }

        UserWallet checkExistingUserWallet = userWalletRepository.findByUserRelationsEmailAndWalletRelationsName(
                userWallet.getUserRelations().getEmail(),
                userWallet.getWalletRelations().getName()
        );

        return checkExistingUserWallet == null;
    }
}
