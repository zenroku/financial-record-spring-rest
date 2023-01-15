package com.zenroku.financial.records.api.app.user.repository;

import com.zenroku.financial.records.api.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}
