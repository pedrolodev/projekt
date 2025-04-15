package dev.jesuspedro.demo.security.users.application;

import dev.jesuspedro.demo.security.users.application.searcher.UserSearcherByCriteria;
import dev.jesuspedro.demo.security.users.application.shared.dto.AppUserCriteria;
import dev.jesuspedro.demo.security.users.infraestructure.persistence.JpaUserRepository;
import dev.jesuspedro.demo.security.users.model.AppUser;
import dev.jesuspedro.demo.security.users.model.AppUserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserSearcherByCriteriaTest {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private UserSearcherByCriteria userSearcherByCriteria;

    @BeforeEach
    void setUp() {
        AppUser user1 = AppUser.builder()
                .name("Juan")
                .email("juan@test.com")
                .username("juantest")
                .password("123")
                .appUserRole(AppUserRole.USER)
                .build();

        AppUser user2 = AppUser.builder()
                .name("Ana")
                .email("ana@test.com")
                .username("anatest")
                .password("456")
                .appUserRole(AppUserRole.ADMIN)
                .build();

        jpaUserRepository.saveAll(List.of(user1, user2));
    }

    @Test
    void shouldReturnUserByUsername() {
        AppUserCriteria criteria = AppUserCriteria.builder()
                .username("juantest")
                .password("123")
                .build();

        List<AppUser> result = userSearcherByCriteria.runList(criteria);

        assertEquals(1, result.size());
        assertEquals("juan@test.com", result.getFirst().getEmail());
    }

    @Test
    void shouldReturnUserByRole() {
        AppUserCriteria criteria = AppUserCriteria.builder()
                .appUserRole(AppUserRole.ADMIN)
                .build();

        List<AppUser> result = userSearcherByCriteria.runList(criteria);

        assertEquals(1, result.size());
        assertEquals("ana@test.com", result.getFirst().getEmail());
    }

    @Test
    void shouldReturnEmptyListIfNoMatch() {
        AppUserCriteria criteria = AppUserCriteria.builder()
                .username("notfound")
                .build();

        List<AppUser> result = userSearcherByCriteria.runList(criteria);

        assertTrue(result.isEmpty());
    }
}
