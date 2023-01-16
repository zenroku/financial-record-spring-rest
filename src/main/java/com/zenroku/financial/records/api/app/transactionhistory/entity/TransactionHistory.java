package com.zenroku.financial.records.api.app.transactionhistory.entity;

import com.zenroku.financial.records.api.app.transactionhistory.model.ActionType;
import com.zenroku.financial.records.api.app.transactionhistory.model.ActionTypeConverter;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @NotBlank(message = "description cannot be empty")
    @Column(name = "description", columnDefinition = "text")
    String description;

    @NotBlank(message = "actionType is required value 'Debit' or 'Credit'")
    @Convert(converter = ActionTypeConverter.class)
    ActionType actionType;

    @NotNull(message = "balance cannot be null")
    @Column(name = "balance")
    Long balance;
}