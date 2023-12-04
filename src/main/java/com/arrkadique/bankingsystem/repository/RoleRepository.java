package com.arrkadique.bankingsystem.repository;

import com.arrkadique.bankingsystem.entity.Role;
import com.arrkadique.bankingsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}