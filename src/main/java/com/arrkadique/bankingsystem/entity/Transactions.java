package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id_from", nullable = false)
    private Long userIdFrom;
    @Column(name = "user_id_to", nullable = false)
    private Long userIdTo;
    @Column(name = "sum", nullable = false)
    private float sum;
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;
    @Column(name = "user_card_id_from", nullable = false)
    private Long userCardIdFrom;
    @Column(name = "user_card_id_to", nullable = false)
    private Long userCardIdTo;
    @Column(name = "status", nullable = false)
    private String status;
}
