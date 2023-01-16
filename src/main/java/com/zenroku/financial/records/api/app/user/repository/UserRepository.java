package com.zenroku.financial.records.api.app.user.repository;

import com.zenroku.financial.records.api.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
}
