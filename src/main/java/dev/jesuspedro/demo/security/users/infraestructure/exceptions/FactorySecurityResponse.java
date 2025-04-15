package dev.jesuspedro.demo.security.users.infraestructure.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class FactorySecurityResponse {
    public static ApiResponse createApiResponse(HttpStatus httpStatus,String message ) {
        return new ApiResponse(httpStatus,message);
    }

    public static ValidationResponse createValidationResponse(HttpStatus httpStatus,List<String> messages) {
        return new ValidationResponse(httpStatus,messages);
    }
}
