package com.zenroku.financial.records.api.app.userwallet.repository;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletRepository extends JpaRepository<UserWallet,Long> {
}
