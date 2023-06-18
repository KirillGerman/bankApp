package ru.meshgroup.bankApplication.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.meshgroup.bankApplication.security.CustomUserDetails;

public class AuthUtils {

    public static Long getAuthenticatedUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return  ((CustomUserDetails) auth.getPrincipal()).getUser().getId();
    }

}
