package com.zenroku.financial.records.api.app.user_wallet.entity;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_wallet")
public class UserWallet extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    User userRelations;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    Wallet walletRelations;

    @NotNull
    @Column(name = "balance")
    Long balance;

    @PrePersist
    void setDefaultBalance(){
        this.setBalance(0L);
    }
}
