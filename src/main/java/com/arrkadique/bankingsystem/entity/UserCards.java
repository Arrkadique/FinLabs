package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Table(name = "user_cards")
@Entity
public class UserCards {
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

}
