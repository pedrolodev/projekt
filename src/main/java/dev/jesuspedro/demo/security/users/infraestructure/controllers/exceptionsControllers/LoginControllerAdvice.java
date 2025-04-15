package dev.jesuspedro.demo.security.users.infraestructure.controllers.exceptionsControllers;

import dev.jesuspedro.demo.security.users.infraestructure.controllers.LoginController;
import dev.jesuspedro.demo.security.users.infraestructure.exceptions.ApiResponse;
import dev.jesuspedro.demo.security.users.infraestructure.exceptions.FactorySecurityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackageClasses = LoginController.class)
public class LoginControllerAdvice {

	@ExceptionHandler(BadCredentialsException.class)
	ResponseEntity<ApiResponse> handleRegistrationException(BadCredentialsException exception) {
		log.info("Login erroneo: {}", exception.getMessage());
		final ApiResponse response = FactorySecurityResponse.createApiResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
