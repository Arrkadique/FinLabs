package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "credit_sum", nullable = false)
    private float creditSum;
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
    @Column(name = "percent", nullable = false)
    private float percent;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "user_card_id", nullable = false)
    private Long userCardId;
    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;

}
