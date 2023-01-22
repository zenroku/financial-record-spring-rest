package com.zenroku.financial.records.api.app.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.customconstraint.uniqueemail.UniqueEmail;
import com.zenroku.financial.records.api.settings.model.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
