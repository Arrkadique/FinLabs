package com.arrkadique.bankingsystem.repository;

import com.arrkadique.bankingsystem.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {
}
