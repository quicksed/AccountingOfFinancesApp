package com.quicksed.accounting_of_finances_app.helper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleChecker {

    public static boolean isAdminUser() {
        List<GrantedAuthority> roles = new ArrayList<>(SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities());

        return roles.stream().anyMatch(p -> Objects.equals(p.getAuthority(), "ROLE_ADMIN"));
    }
}
