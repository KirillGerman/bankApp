package ru.meshgroup.bankApplication.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    public boolean authAndHasUserId(Authentication authentication, Long userId) {
        return  authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken) &&
                    ((CustomUserDetails) authentication.getPrincipal()).getUser().getId().equals(userId);
    }
}