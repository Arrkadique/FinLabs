package com.arrkadique.bankingsystem.repository;

import com.arrkadique.bankingsystem.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCardsRepository extends JpaRepository<UserCard, Long> {
    UserCard findByCardNumber(String CardNumber);

    List<UserCard> findAllByUserId(Long id);
}
