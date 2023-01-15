package com.zenroku.financial.records.api;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.app.user_wallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.app.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@SpringBootTest
public class CreateUserTest {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Disabled
    void createNewUserWithUserWallet(){
        User user = new User();
        user.setFirstName("Zahir");
        user.setLastName("Fathurrahman");
        user.setEmail("zahir.frahman@gmail.com");

        Set<UserWallet> userWallets = new HashSet<>();

        List<Wallet> allWallets = walletRepository.findAll();
        allWallets.stream().forEach(wallet -> {
            UserWallet userWallet = new UserWallet();
            userWallet.setUserRelations(user);
            userWallet.setWalletRelations(wallet);
            userWallets.add(userWallet);
        });

        user.setUserWalletRelations(userWallets);

        userRepository.save(user);
    }
}
