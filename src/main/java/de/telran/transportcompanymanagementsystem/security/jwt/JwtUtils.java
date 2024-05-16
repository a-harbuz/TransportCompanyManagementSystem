package de.telran.transportcompanymanagementsystem.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for handling JWT-related operations.
 * This utility class provides a method for generating a
 * {@link JwtAuthentication} object from JWT claims.
 */
@Component
public class JwtUtils {
    public static JwtAuthentication generate(Claims claims) {
        // Extract the username from the claims
        String username = claims.getSubject();
        // Extract the roles list from the claims
        List<?> rolesObjectList = claims.get("roles", List.class);
        // Convert the roles list to a list of strings
        List<String> roles = rolesObjectList.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        // Create and return a JwtAuthentication object with the extracted information
        return new JwtAuthentication(username, roles);
    }
}


