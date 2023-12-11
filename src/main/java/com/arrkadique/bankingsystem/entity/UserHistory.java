package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "action_date", nullable = false)
    private Date actionDate;
    @Column(name = "action", nullable = false)
    private String action;
}
