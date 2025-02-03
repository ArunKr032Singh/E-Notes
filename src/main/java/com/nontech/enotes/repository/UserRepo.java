package com.nontech.enotes.repository;

import com.nontech.enotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);
}
