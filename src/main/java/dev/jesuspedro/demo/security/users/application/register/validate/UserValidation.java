package dev.jesuspedro.demo.security.users.application.register.validate;

import dev.jesuspedro.demo.security.users.infraestructure.dto.RegistrationRequest;
import dev.jesuspedro.demo.security.users.infraestructure.persistence.JpaUserRepository;
import dev.jesuspedro.demo.security.users.model.exceptions.UserEmailExists;
import dev.jesuspedro.demo.security.users.model.exceptions.UserUsernameExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidation {

    private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";
    private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";
    private final JpaUserRepository jpaUserRepository;

    public void run(RegistrationRequest registrationRequest) {
        final String email = registrationRequest.getEmail();
        final String username = registrationRequest.getUsername();
        checkEmail(email);
        checkUsername(username);
    }

    private void checkUsername(String username) {

        final boolean existsByUsername = jpaUserRepository.existsByUsername(username);

        if (existsByUsername) {
            throw new UserUsernameExists();
        }

    }

    private void checkEmail(String email) {

        final boolean existsByEmail = jpaUserRepository.existsByEmail(email);

        if (existsByEmail) {
            throw new UserEmailExists();
        }
    }


}
