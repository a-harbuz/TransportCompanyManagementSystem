package de.telran.transportcompanymanagementsystem.security.service;

import de.telran.transportcompanymanagementsystem.security.jwt.JwtProvider;
import de.telran.transportcompanymanagementsystem.security.jwt.JwtRequest;
import de.telran.transportcompanymanagementsystem.security.jwt.JwtResponse;
import de.telran.transportcompanymanagementsystem.security.model.UserDto;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for handling authentication-related operations.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    /**
     * A map for storing refresh tokens against user logins.
     */
    private final Map<String, String> refreshStorage = new HashMap<>();

    /**
     * The JWT provider for generating and validating JWT tokens.
     */
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    /**
     * Handles user login and returns JWT tokens upon successful authentication.
     */
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        // Get UserDto with information about Employee from DB
        final UserDto userDto = userService.getByLoginWithDb(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User is not found"));

        if (passwordEncoder.matches(authRequest.getPassword(), userDto.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(userDto);
            final String refreshToken = jwtProvider.generateRefreshToken(userDto);
            refreshStorage.put(userDto.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    /**
     * Generates a new access token using a valid refresh token.
     */
    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        // Validate the provided refresh token
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            // Extract claims from the refresh token
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            // Get the user login from the token claims
            final String login = claims.getSubject();
            // Retrieve the stored refresh token for the user
            final String savedRefreshToken = refreshStorage.get(login);
            // Compare the stored refresh token with the provided token
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                // Fetch the user data
                final UserDto userDto = userService.getByLoginWithDb(login)
                        .orElseThrow(() -> new AuthException("User is not found"));
                // Generate a new access token
                final String accessToken = jwtProvider.generateAccessToken(userDto);
                // Return a JwtResponse with the new access token
                return new JwtResponse(accessToken, null);
            }
        }
        // Return a JwtResponse with null values if validation fails
        return new JwtResponse(null, null);
    }


    /**
     * Refreshes both access and refresh tokens using a valid refresh token.
     */
    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        // Validate the provided refresh token
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            // Extract claims from the refresh token
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            // Get the user login from the token claims
            final String login = claims.getSubject();
            // Retrieve the stored refresh token for the user
            final String savedRefreshToken = refreshStorage.get(login);
            // Compare the stored refresh token with the provided token
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                // Fetch the user data
                final UserDto userDto = userService.getByLoginWithDb(login)
                        .orElseThrow(() -> new AuthException("User is not found"));
                // Generate new access and refresh tokens
                final String accessToken = jwtProvider.generateAccessToken(userDto);
                final String newRefreshToken = jwtProvider.generateRefreshToken(userDto);
                // Update the stored refresh token for the user
                refreshStorage.put(userDto.getLogin(), newRefreshToken);
                // Return a JwtResponse with the new access and refresh tokens
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        // Throw an AuthException if validation fails
        throw new AuthException("Invalid JWT token");
    }
}
