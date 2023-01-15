package com.zenroku.financial.records.api.app.user.entity;

import com.zenroku.financial.records.api.app.user_wallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "`user`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User extends BaseEntity {

    @NotBlank
    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Email
    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @OneToMany(mappedBy = "userRelations", cascade = { CascadeType.PERSIST })
    Set<UserWallet> userWalletRelations;
}
