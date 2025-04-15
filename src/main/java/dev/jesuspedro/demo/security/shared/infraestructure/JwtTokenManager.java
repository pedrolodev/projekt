package dev.jesuspedro.demo.security.shared.infraestructure;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.jesuspedro.demo.security.shared.model.TokenManager;
import dev.jesuspedro.demo.security.users.model.AppUser;
import dev.jesuspedro.demo.security.users.model.AppUserRole;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
public class JwtTokenManager implements TokenManager {

	@Value("${jwt.issuer}")
	private String issuer;

	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.expirationMinute}")
	private long expirationMinute;

	@Override
	public String generateToken(AppUser appUser) {

		final String username = appUser.getUsername();
		final AppUserRole appUserRole = appUser.getAppUserRole();

		return JWT.create()
				.withSubject(username)
				.withIssuer(issuer)
				.withClaim("role", appUserRole.name())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + expirationMinute * 60 * 1000))
				.sign(Algorithm.HMAC256(secretKey.getBytes()));
	}

	@Override
	public String getUsernameFromToken(String token) {

		final DecodedJWT decodedJWT = getDecodedJWT(token);

		return decodedJWT.getSubject();
	}

	@Override
	public boolean validateToken(String token, String authenticatedUsername) {

		final String usernameFromToken = getUsernameFromToken(token);

		final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
		final boolean tokenExpired = isTokenExpired(token);

		return equalsUsername && !tokenExpired;
	}

	private boolean isTokenExpired(String token) {

		final Date expirationDateFromToken = getExpirationDateFromToken(token);
		return expirationDateFromToken.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {

		final DecodedJWT decodedJWT = getDecodedJWT(token);

		return decodedJWT.getExpiresAt();
	}

	private DecodedJWT getDecodedJWT(String token) {

		final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey.getBytes())).build();

		return jwtVerifier.verify(token);
	}

}
