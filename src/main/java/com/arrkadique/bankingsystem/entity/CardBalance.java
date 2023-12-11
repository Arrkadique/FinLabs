package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Entity
@Table(name = "card_balance")
@Getter
@Setter
public class CardBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "card_id", nullable = false)
    private Long cardId;
    @Column(name = "balance", nullable = false)
    private float balance;

    public CardBalance(Long cardId) {
        this.cardId = cardId;
        this.balance = 0;
    }
}
