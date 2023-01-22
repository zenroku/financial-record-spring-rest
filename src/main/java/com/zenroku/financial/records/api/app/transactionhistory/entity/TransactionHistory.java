package com.zenroku.financial.records.api.app.transactionhistory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zenroku.financial.records.api.app.transactionhistory.model.ActionType;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "transaction_history")
public class TransactionHistory extends BaseEntity {

    @JsonIgnore
    @JoinColumn(name = "user_wallet_id")
    @ManyToOne
    UserWallet userWalletRelations;

    @NotBlank(message = "description cannot be empty")
    @Column(name = "description", columnDefinition = "text")
    String description;

    @Enumerated(EnumType.STRING)
    ActionType actionType;

    @NotNull(message = "actionBalance cannot be null")
    @Column(name = "action_balance", columnDefinition = "numeric(38,2) default 0")
    BigDecimal actionBalance;

    @Column(name = "current_balance", columnDefinition = "numeric(38,2) default 0")
    BigDecimal currentBalance;

    @Column(name = "result_balance", columnDefinition = "numeric(38,2) default 0")
    BigDecimal resultBalance;

    @Column(name = "transaction_datetime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDatetime;

    @Transient
    @NotNull(message = "useWalletId cannot be null")
    Long userWalletId;

    public Long getUserWalletId(){
        if (this.userWalletRelations != null){
            return this.userWalletRelations.getId();
        }
        return this.userWalletId;
    }
}
