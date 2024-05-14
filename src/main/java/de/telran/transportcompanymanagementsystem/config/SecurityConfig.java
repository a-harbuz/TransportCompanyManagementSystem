package de.telran.transportcompanymanagementsystem.config;

import de.telran.transportcompanymanagementsystem.security.UserDetailsServiceImpl;
import de.telran.transportcompanymanagementsystem.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static de.telran.transportcompanymanagementsystem.security.RoleAuthList.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider getAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//                .authorizeHttpRequests(
//                        auth -> auth
//                                .requestMatchers("/auth/login", "/auth/token",
//                                        "/swagger-ui.html",
//                                        "/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**"
//                                )
//                                .permitAll()
//
//                                .requestMatchers("/company/all"
//                                ).hasAnyRole("DEVELOPER"))
//
//                                //.anyRequest().authenticated())
//                                //.anyRequest().denyAll())

                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(USER_LIST).permitAll()
                                .requestMatchers(DRIVER_LIST).hasAnyRole(DRIVER_ROLE, MANAGER_ROLE, OWNER_ROLE, DEVELOPER_ROLE)
                                .requestMatchers(MANAGER_LIST).hasAnyRole(MANAGER_ROLE, OWNER_ROLE, DEVELOPER_ROLE)
                                .requestMatchers(OWNER_LIST).hasAnyRole(OWNER_ROLE, DEVELOPER_ROLE)
                                .requestMatchers(DEVELOPER_LIST).hasAnyRole(DEVELOPER_ROLE)
                                .anyRequest().denyAll()
                )
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());
        //.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
