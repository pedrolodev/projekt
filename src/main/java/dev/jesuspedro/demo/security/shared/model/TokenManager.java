package dev.jesuspedro.demo.security.shared.model;

import dev.jesuspedro.demo.security.users.model.AppUser;

public interface TokenManager {
    String generateToken(AppUser appUser);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, String authenticatedUsername);
}
