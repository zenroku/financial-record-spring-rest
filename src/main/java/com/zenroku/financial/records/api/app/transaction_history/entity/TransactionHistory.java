package com.zenroku.financial.records.api.app.transaction_history.entity;

import com.zenroku.financial.records.api.app.user_wallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "transaction_history")
public class TransactionHistory extends BaseEntity {

    @NotNull
    @JoinColumn(name = "user_wallet_id")
    @ManyToOne
    UserWallet userWalletRelations;

    @NotNull
    @Column(name = "description", columnDefinition = "text")
    String description;

    @NotNull
    @Column(name = "balance")
    Long balance;
}
