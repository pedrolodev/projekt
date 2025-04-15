package dev.jesuspedro.demo.security.users.application.register;

import dev.jesuspedro.demo.security.users.application.shared.mappers.AppUserMapperImpl;
import dev.jesuspedro.demo.security.users.application.register.validate.UserValidation;
import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationRequest;
import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationResponse;
import dev.jesuspedro.demo.security.users.infraestructure.persistence.JpaUserRepository;
import dev.jesuspedro.demo.security.users.model.AppUser;
import dev.jesuspedro.demo.security.users.model.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserRegister {

    private final UserValidation userValidation;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JpaUserRepository jpaUserRepository;
    private final AppUserMapperImpl appUserMapper;

    public RegistrationResponse run(RegistrationRequest registrationRequest){
        userValidation.run(registrationRequest);

        final AppUser appUser = appUserMapper.registrationRequestToAppUser(registrationRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setAppUserRole(AppUserRole.USER);
        jpaUserRepository.save(appUser);


        log.info("{} registered successfully!", appUser.getName());

        return new RegistrationResponse("User saved correctly");
    }
}
