package com.zenroku.financial.records.api.app.wallet.repository;

import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findByName(String name);
}
