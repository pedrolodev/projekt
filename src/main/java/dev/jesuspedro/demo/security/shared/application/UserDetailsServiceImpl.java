package dev.jesuspedro.demo.security.shared.application;


import dev.jesuspedro.demo.security.users.application.searcher.UserSearcherByCriteria;
import dev.jesuspedro.demo.security.users.application.shared.dto.AppUserCriteria;
import dev.jesuspedro.demo.security.users.model.AppUser;
import dev.jesuspedro.demo.security.users.model.AppUserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	private final UserSearcherByCriteria userSearcher;

	@Override
	public UserDetails loadUserByUsername(String username) {

		Optional<AppUser> optionalUser = userSearcher.run(
				AppUserCriteria.builder().username(username).build()
		);

		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
		}

		AppUser user = optionalUser.get();

		String authenticatedUsername = user.getUsername();
		String authenticatedPassword = user.getPassword();
		AppUserRole appUserRole = user.getAppUserRole();
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUserRole.name());
		return new User(
				authenticatedUsername,
				authenticatedPassword,
				Collections.singletonList(grantedAuthority)
		);
	}
}
