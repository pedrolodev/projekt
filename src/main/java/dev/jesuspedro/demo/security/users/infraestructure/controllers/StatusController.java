package dev.jesuspedro.demo.security.users.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<String> status() {

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
