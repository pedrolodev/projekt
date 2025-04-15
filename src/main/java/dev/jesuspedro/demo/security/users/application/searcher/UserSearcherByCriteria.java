package dev.jesuspedro.demo.security.users.application.searcher;

import dev.jesuspedro.demo.security.users.application.shared.dto.AppUserCriteria;
import dev.jesuspedro.demo.security.users.infraestructure.persistence.JpaUserRepository;
import dev.jesuspedro.demo.security.users.infraestructure.persistence.UserSpecifications;
import dev.jesuspedro.demo.security.users.model.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSearcherByCriteria {

    private final JpaUserRepository jpaUserRepository;

    public List<AppUser> runList(AppUserCriteria appUser) {
        Specification<AppUser> spec = Specification.where(null);
        if(appUser.getName() != null) spec = spec.and(UserSpecifications.hasName(appUser.getName()));
        if(appUser.getPassword() != null) spec = spec.and(UserSpecifications.hasPassword(appUser.getPassword()));
        if(appUser.getUsername() != null) spec = spec.and(UserSpecifications.hasUsername(appUser.getUsername()));
        if(appUser.getEmail() != null) spec = spec.and(UserSpecifications.hasEmail(appUser.getEmail()));
        if(appUser.getAppUserRole() != null) spec = spec.and(UserSpecifications.hasRole(appUser.getAppUserRole()));
        return jpaUserRepository.findAll(spec);
    }

    public Optional<AppUser> run(AppUserCriteria appUser){
        return runList(appUser).stream().findFirst();
    }

}
