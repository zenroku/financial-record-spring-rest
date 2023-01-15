package com.zenroku.financial.records.api.app.user_wallet.repository;

import com.zenroku.financial.records.api.app.user_wallet.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletRepository extends JpaRepository<UserWallet,Long> {
}
