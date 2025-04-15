package dev.jesuspedro.demo.security.users.infraestructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest {

	@NotEmpty
	private String name;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

}
