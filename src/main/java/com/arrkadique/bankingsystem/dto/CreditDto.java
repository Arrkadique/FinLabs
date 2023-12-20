package com.arrkadique.bankingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditDto {
    String monthPayment;
    String sum;
    String percent;
    String date;
}
