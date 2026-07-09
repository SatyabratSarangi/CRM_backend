package com.satya.CRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token; // For simplicity we might just return username or success msg
    private String username;
    private String role;
    private String message;
}
