package com.zenroku.financial.records.api.app.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zenroku.financial.records.api.app.user_wallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.custom_constraint.annotation.UniqueEmail;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "`user`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User extends BaseEntity {

    @NotBlank(message = "firstName cannot be empty")
    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @UniqueEmail
    @Email(message = "please use correct email format")
    @NotBlank(message = "email cannot be empty")
    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "userRelations", cascade = { CascadeType.PERSIST })
    Set<UserWallet> userWalletRelations;
}
