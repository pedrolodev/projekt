package dev.jesuspedro.demo.security.users.model.exceptions;

import org.springframework.http.HttpStatus;



public class UserEmailExists extends AppUserException {

	public UserEmailExists() {
		super("USER EMAIL EXISTS",HttpStatus.BAD_REQUEST);
	}
}
