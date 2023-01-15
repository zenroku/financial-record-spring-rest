package com.zenroku.financial.records.api.app.wallet.entity;

import com.zenroku.financial.records.api.settings.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@Entity
@Table(name = "wallet", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Wallet extends BaseEntity {

    @NotBlank
    @Column(name = "name")
    String name;
}
