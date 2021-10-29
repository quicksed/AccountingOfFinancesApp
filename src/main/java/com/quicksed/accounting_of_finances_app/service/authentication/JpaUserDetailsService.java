package com.quicksed.accounting_of_finances_app.service.authentication;

import com.quicksed.accounting_of_finances_app.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private static final String EX_MSG_TMPL_USER_NOT_FOUND = "[email = %s] User not found";

    private final UserService userService;

    public JpaUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = userService.findAuthenticationInfo(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(EX_MSG_TMPL_USER_NOT_FOUND, username)));

        return User.builder()
                .username(userInfo.getEmail())
                .password(userInfo.getPassword())
                .roles(userInfo.getRoleCodes().toArray(new String[0]))
                .build();
    }
}
