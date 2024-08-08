package com.example.tradingplatform.repository;

import com.example.tradingplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   public User findByEmail(String email);
}
