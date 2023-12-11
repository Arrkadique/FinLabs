package com.arrkadique.bankingsystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private String username;
    private String lastName;
    private String password;
    private String phoneNumber;

}
