package dev.jesuspedro.demo.security.users.application.shared.mappers;

import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationRequest;
import dev.jesuspedro.demo.security.users.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapperImpl implements AppUserMapper{
    @Override
    public AppUser registrationRequestToAppUser(RegistrationRequest registrationRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registrationRequest.getUsername());
        appUser.setName(registrationRequest.getName());
        appUser.setPassword(registrationRequest.getPassword());
        appUser.setEmail(registrationRequest.getEmail());
        return appUser;
    }
}
