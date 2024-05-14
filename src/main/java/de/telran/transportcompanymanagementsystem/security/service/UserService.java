package de.telran.transportcompanymanagementsystem.security.service;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.repository.EmployeeInfoRepository;
import de.telran.transportcompanymanagementsystem.security.model.UserDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for handling user-related operations.
 * This service provides methods for fetching user data based on login credentials.
 * It initializes a list of users for demonstration purposes.
 */
@Service
public class UserService {

    public UserService() {
    }

//    public Optional<UserDto> getByLogin(@NonNull String login) {
//        return getByLoginWithDb(login); // database search
//    }

    // с БД
    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    public Optional<UserDto> getByLoginWithDb(@NonNull String login) {
        EmployeeInfo employeeInfo = employeeInfoRepository.findByLogin(login);
        Optional<UserDto> userDto = Optional.empty();

        if(employeeInfo !=null) {
            userDto = Optional.of(UserDto.builder()
                    .login(employeeInfo.getLogin())
                    .password(employeeInfo.getPassword())
                    .firstName(employeeInfo.getLogin())
                    //.lastName(userEntity.getLastName())
//                    .roles(
//                            Arrays.stream(employeeInfo.getRoles().toArray()) //.split(",")
//                                    .distinct()
//                                    .map(s -> Role.valueOf(s))
//                                    .collect(Collectors.toSet())
//                    )
                    .build());
        }
        userDto.get().setRoles(employeeInfo.getRoles());
        return userDto;
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;
}
