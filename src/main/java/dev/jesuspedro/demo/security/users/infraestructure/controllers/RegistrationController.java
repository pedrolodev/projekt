package dev.jesuspedro.demo.security.users.infraestructure.controllers;

import dev.jesuspedro.demo.security.users.application.register.UserRegister;
import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationRequest;
import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserRegister userRegister;

    @PostMapping()
    public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

        final RegistrationResponse registrationResponse = userRegister.run(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

}