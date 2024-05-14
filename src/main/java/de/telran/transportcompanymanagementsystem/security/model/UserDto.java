package de.telran.transportcompanymanagementsystem.security.model;

import de.telran.transportcompanymanagementsystem.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    /**
     * The username (login) of the user.
     */
    private String login;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The roles assigned to the user.
     */
    private Set<Role> roles;
}
