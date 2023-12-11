package com.arrkadique.bankingsystem.repository;

import com.arrkadique.bankingsystem.entity.CardBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBalanceRepository extends JpaRepository<CardBalance, Long> {
    void deleteByCardId(Long cardId);

    CardBalance findByCardId(Long cardId);
}
