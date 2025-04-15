package dev.jesuspedro.demo.security.users.infraestructure.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationResponse extends Response{

    private final List<String> messages;

    public ValidationResponse(HttpStatus status, List<String> messages) {
        super(status);
        this.messages = messages;
    }
}
