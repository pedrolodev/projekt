package dev.jesuspedro.demo.security.users.application.shared.dto;

import dev.jesuspedro.demo.security.users.model.AppUserRole;
import lombok.*;

@Data
@Builder
public class AppUserCriteria {

    private String name;
    private String username;
    private String password;
    private String email;
    private AppUserRole appUserRole;

}