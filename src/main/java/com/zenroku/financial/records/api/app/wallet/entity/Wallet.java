package com.zenroku.financial.records.api.app.wallet.entity;

import com.zenroku.financial.records.api.settings.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "wallet", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Wallet extends BaseEntity {

    @NotBlank(message = "name cannot be empty")
    @Column(name = "name")
    String name;
}
