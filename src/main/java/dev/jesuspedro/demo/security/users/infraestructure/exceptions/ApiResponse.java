package dev.jesuspedro.demo.security.users.infraestructure.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse extends Response {
    private final String message;

    public ApiResponse(HttpStatus status,String message) {
        super(status);
        this.message = message;
    }
}
