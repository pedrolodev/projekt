package dev.jesuspedro.demo.security.users.application.login;

import dev.jesuspedro.demo.security.shared.infraestructure.JwtTokenManager;
import dev.jesuspedro.demo.security.users.application.searcher.UserSearcherByCriteria;
import dev.jesuspedro.demo.security.users.application.shared.dto.AppUserCriteria;
import dev.jesuspedro.demo.security.users.infraestructure.dto.LoginRequest;
import dev.jesuspedro.demo.security.users.infraestructure.dto.LoginResponse;
import dev.jesuspedro.demo.security.users.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserLogin {

    private final UserSearcherByCriteria userSearcher;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationManager authenticationManager;

    public LoginResponse run(LoginRequest loginRequest){

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final AppUser appUser = userSearcher.run(AppUserCriteria.builder().username(username).build())
                .orElseGet(() -> AppUser.builder().build());

        final String token = jwtTokenManager.generateToken(appUser);

        return new LoginResponse(token);
    }
}
