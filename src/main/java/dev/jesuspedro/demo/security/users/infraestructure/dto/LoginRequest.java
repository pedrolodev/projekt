package dev.jesuspedro.demo.security.users.infraestructure.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginRequest {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}
