package de.telran.transportcompanymanagementsystem.security.service;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.repository.EmployeeInfoRepository;
import de.telran.transportcompanymanagementsystem.security.model.UserDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for handling user-related operations.
 * This service provides methods for fetching user data based on login credentials.
 * It initializes a list of users for demonstration purposes.
 */
@Service
public class UserService {

    public UserService() {
    }

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
                    .build());
        }
        userDto.get().setRoles(employeeInfo.getRoles());
        return userDto;
    }
}
