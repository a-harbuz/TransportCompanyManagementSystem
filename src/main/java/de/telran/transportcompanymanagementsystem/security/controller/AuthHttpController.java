package de.telran.transportcompanymanagementsystem.security.controller;

import de.telran.transportcompanymanagementsystem.security.jwt.JwtRequest;
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
@RequestMapping("")
@RequiredArgsConstructor
public class AuthHttpController {

    private final AuthService authService;

    @PostMapping("/login")
    //JwtResponse
    public ResponseEntity<String> login(@RequestBody JwtRequest authRequest) throws AuthException {
        System.out.println(">>>>>> login >>>>>>>>>");
        System.out.println(authRequest.getLogin());
        System.out.println(authRequest.getPassword());
        // Генерация токена
        final JwtResponse token = authService.login(authRequest);

        // Создание заголовка Set-Cookie для добавления токена в куки
        ResponseCookie cookie = ResponseCookie.from("token", token.getAccessToken())
                .httpOnly(true) // Установка флага HttpOnly для защиты от XSS-атак
                .path("/") // Установка пути куки
                .maxAge(3600) // Время жизни куки в секундах
                .build();

        // Создание заголовков ответа
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        // Возвращение ответа с токеном в куки
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(token.getAccessToken());
    }
}
