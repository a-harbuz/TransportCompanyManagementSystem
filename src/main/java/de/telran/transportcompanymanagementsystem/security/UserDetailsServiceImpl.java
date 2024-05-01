package de.telran.transportcompanymanagementsystem.security;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.entity.Role;
import de.telran.transportcompanymanagementsystem.repository.EmployeeInfoRepository;
import de.telran.transportcompanymanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //private final EmployeeRepository employeeRepository;
    private final EmployeeInfoRepository employeeInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeInfo employeeInfo = employeeInfoRepository.findByLogin(username);
        if (employeeInfo==null) {
            throw new UsernameNotFoundException("User with login '" + username + "' not found");
        }

        UserDetails userDetails = User.withUsername(username)
                .username(employeeInfo.getLogin())
                .password(employeeInfo.getPassword())
                .authorities(getAuthorities(employeeInfo.getRoles()))
                .build();
        System.out.println(">>>> UserDetails >>>");
        System.out.println(userDetails);
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            role.getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()))
            );
//            System.out.println(">>> ROLE >>>");
//            System.out.println(role);
        }

//        for (GrantedAuthority auth : authorities) {
//            System.out.println(">>auth>>>");
//            System.out.println(auth);
//        }
        return authorities;
    }
}