package de.telran.transportcompanymanagementsystem.security.controller;

import de.telran.transportcompanymanagementsystem.security.jwt.JwtRequest;
import de.telran.transportcompanymanagementsystem.security.jwt.JwtRequestRefresh;
import de.telran.transportcompanymanagementsystem.security.jwt.JwtResponse;
import de.telran.transportcompanymanagementsystem.security.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The authentication service bean for handling authentication operations.
     */
    private final AuthService authService;

    /**
     * Handles user login and returns a JWT response.
     *
     * @param authRequest the authentication request containing username and password.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if authentication fails.
     */
    @GetMapping("/login")
    //JwtResponse
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        // get Token
        final JwtResponse token = authService.login(authRequest);

        ResponseCookie cookie = ResponseCookie.from("token", token.getAccessToken())
                .httpOnly(true) // Setting the HttpOnly flag to protect against XSS attacks
                .path("/") // Setting the cookie path
                .maxAge(3600) // Cookie lifetime in seconds
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(token);
    }

    /**
     * Obtains a new access token using a refresh token.
     *
     * @param request the request containing the refresh token.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if token refresh fails.
     */
    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(
            @RequestBody JwtRequestRefresh request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    /**
     * Obtains a new refresh token using an existing refresh token.
     *
     * @param request the request containing the refresh token.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if token refresh fails.
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRequestRefresh request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
