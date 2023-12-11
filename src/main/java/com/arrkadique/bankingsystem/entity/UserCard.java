package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Table(name = "user_cards")
@Entity
@Getter
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
    private Date cardDate;
    @Column(name = "cvv", nullable = false)
    private String cvv;

    public UserCard(Long userId, String cardNumber, Date cardDate, String cvv) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cvv = cvv;
    }
}
