package dev.jesuspedro.demo.security.users.infraestructure.controllers.exceptionsControllers;

import dev.jesuspedro.demo.security.users.infraestructure.controllers.RegistrationController;
import dev.jesuspedro.demo.security.users.infraestructure.exceptions.ApiResponse;
import dev.jesuspedro.demo.security.users.infraestructure.exceptions.FactorySecurityResponse;
import dev.jesuspedro.demo.security.users.model.exceptions.AppUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackageClasses = RegistrationController.class)
public class RegistrationControllerAdvice {

	@ExceptionHandler(AppUserException.class)
	ResponseEntity<ApiResponse> handleRegistrationException(AppUserException exception) {
		log.info("RegistrationException: {}", exception.getErrorMessage());
		final ApiResponse response = FactorySecurityResponse.createApiResponse(exception.getHttpStatus(), exception.getErrorMessage());
		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
