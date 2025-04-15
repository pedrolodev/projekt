package dev.jesuspedro.demo.security.users.infraestructure.controllers.exceptionsControllers;

import dev.jesuspedro.demo.security.users.infraestructure.exceptions.FactorySecurityResponse;
import dev.jesuspedro.demo.security.users.infraestructure.exceptions.ValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class ValidationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

		final List<String> errorList =  exception.getBindingResult().getFieldErrors().stream().
				map((error)->MessageFormat.format("Key:{0} - Value:{1}",error.getField(),error.getDefaultMessage()))
				.toList();
		
        final ValidationResponse validationErrorResponse =
				FactorySecurityResponse.createValidationResponse(HttpStatus.BAD_REQUEST, errorList);

		log.warn("Validation errors : {} , Parameters : {}", errorList, exception.getBindingResult().getTarget());

		return ResponseEntity.status(validationErrorResponse.getStatus()).body(validationErrorResponse);
	}

}
