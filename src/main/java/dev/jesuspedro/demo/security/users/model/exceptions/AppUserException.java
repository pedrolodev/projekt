package dev.jesuspedro.demo.security.users.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public abstract class AppUserException extends RuntimeException{
    protected String errorMessage;
    protected HttpStatus httpStatus;
}
