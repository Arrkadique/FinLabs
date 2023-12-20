package com.arrkadique.bankingsystem.repository;

import com.arrkadique.bankingsystem.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    Credit findByUserId(Long userId);

    List<Credit> findAllByUserId(Long userId);
}
