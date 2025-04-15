package dev.jesuspedro.demo.security.users.infraestructure.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

	@NotEmpty(message = "no empty")
	private String message;

}
