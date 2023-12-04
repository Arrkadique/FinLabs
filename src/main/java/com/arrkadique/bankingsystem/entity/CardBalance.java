package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Table(name = "card_balance")
public class CardBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "card_id", nullable = false)
    private Long cardId;
    @Column(name = "balance", nullable = false)
    private float balance;

}
