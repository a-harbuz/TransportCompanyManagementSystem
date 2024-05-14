package de.telran.transportcompanymanagementsystem.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object (DTO) for carrying JWT tokens in response to a successful authentication.
 */
@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}

