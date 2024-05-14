package de.telran.transportcompanymanagementsystem.security.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for carrying the refresh token.
 */
@Getter
@Setter
public class JwtRequestRefresh {
    public String refreshToken;
}


