package dev.jesuspedro.demo.security.users.infraestructure.controllers;

import dev.jesuspedro.demo.security.users.application.login.UserLogin;
import dev.jesuspedro.demo.security.users.infraestructure.dto.LoginRequest;
import dev.jesuspedro.demo.security.users.infraestructure.dto.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
public class LoginController {

    private final UserLogin userLogin;

    @PostMapping()
    public ResponseEntity<LoginResponse> registrationRequest(@Valid @RequestBody LoginRequest loginRequest) {
        final LoginResponse loginResponse = userLogin.run(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

}