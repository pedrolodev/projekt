package dev.jesuspedro.demo.security.users.infraestructure.persistence;

import dev.jesuspedro.demo.security.users.model.AppUser;
import dev.jesuspedro.demo.security.users.model.AppUserRole;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<AppUser> hasName(String name) {
        return (root, query, builder) ->
                name == null ? null : builder.equal(root.get("name"), name);
    }

    public static Specification<AppUser> hasUsername(String username) {
        return (root, query, builder) ->
                username == null ? null : builder.equal(root.get("username"), username);
    }

    public static Specification<AppUser> hasPassword(String password) {
        return (root, query, builder) ->
                password == null ? null : builder.equal(root.get("password"), password);
    }

    public static Specification<AppUser> hasEmail(String email) {
        return (root, query, builder) ->
                email == null ? null : builder.equal(root.get("email"), email);
    }

    public static Specification<AppUser> hasRole(AppUserRole role) {
        return (root, query, builder) ->
                role == null ? null : builder.equal(root.get("appUserRole"), role);
    }
}