package de.telran.transportcompanymanagementsystem.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {
    private String login;
    private String password;
}

