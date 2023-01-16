package com.zenroku.financial.records.api.app.userwallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.userwallet.constraint.uniqwalletperuser.UniqWalletPerUser;
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
@UniqWalletPerUser
public class UserWallet extends BaseEntity {

    @JsonIgnore
    @NotNull(message = "userId cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id")
    User userRelations;

    @JsonIgnore
    @NotNull(message = "walletId cannot be null")
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    Wallet walletRelations;

    @NotNull(message = "balance cannot be null")
    @Column(name = "balance")
    Long balance;

    @Transient
    Long userId;

    @Transient
    Long walletId;

    public Long getUserId() {
        if (this.userId != null) return this.userId;
        if (userRelations != null && userRelations.getId() != null){
            return userRelations.getId();
        }
        return null;
    }

    public Long getWalletId() {
        if (this.walletId != null) return this.walletId;
        if (walletRelations != null && walletRelations.getId() != null){
            return walletRelations.getId();
        }
        return null;
    }
}
