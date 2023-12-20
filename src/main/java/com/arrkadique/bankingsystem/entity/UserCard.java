package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Table(name = "user_cards")
@Entity
@Getter
@Setter
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "card_number", nullable = false)
    private String cardNumber;
    @Column(name = "card_date", nullable = false)
    private String cardDate;
    @Column(name = "cvv", nullable = false)
    private String cvv;
    @Column(name = "balance", nullable = false)
    private float balance;

    public UserCard(Long userId, String cardNumber, String cardDate, String cvv, float balance) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cvv = cvv;
        this.balance = balance;
    }
}
