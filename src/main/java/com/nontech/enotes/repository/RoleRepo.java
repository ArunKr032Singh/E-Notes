package com.nontech.enotes.repository;

import com.nontech.enotes.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
