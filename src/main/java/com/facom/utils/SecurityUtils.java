package com.facom.utils;

import com.facom.domain.UserEnvironment;
import com.facom.domain.security.UserAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getCurrentUserLogin() {
        return getCurrentUserEnvironment().getLogin();
    }

    public static Long getCurrentUserId() {
        return getCurrentUserEnvironment().getId();
    }

    private static UserEnvironment getCurrentUserEnvironment() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UserAuthentication) {

            return ((UserAuthentication) auth).getUserEnvironment();
        }
        throw new IllegalStateException("Invoke current user without authentication!");
    }
}
