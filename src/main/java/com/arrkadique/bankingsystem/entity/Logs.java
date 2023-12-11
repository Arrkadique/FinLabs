package com.arrkadique.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "logs")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "action_date", nullable = false)
    private Date actionDate;
    @Column(name = "action", nullable = false)
    private String action;

}
