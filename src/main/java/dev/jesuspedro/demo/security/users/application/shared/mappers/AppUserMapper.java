package dev.jesuspedro.demo.security.users.application.shared.mappers;


import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationRequest;
import dev.jesuspedro.demo.security.users.model.AppUser;

public interface AppUserMapper {
    AppUser registrationRequestToAppUser(RegistrationRequest registrationRequest);
}
