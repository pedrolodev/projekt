package dev.jesuspedro.demo.security.users.infraestructure.persistence;

import dev.jesuspedro.demo.security.users.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}