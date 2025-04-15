package dev.jesuspedro.demo.security.users.model.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserUsernameExists extends AppUserException {

	public UserUsernameExists() {
		super("USERNAME EXISTS", HttpStatus.BAD_REQUEST);
	}
}
