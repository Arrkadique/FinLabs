package com.arrkadique.bankingsystem.repository;

import com.arrkadique.bankingsystem.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
