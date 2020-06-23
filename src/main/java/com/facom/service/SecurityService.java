package com.facom.service;

import com.facom.cache.SecurityCache;
import com.facom.exception.UserSecurityTokenException;
import com.facom.model.security.UserAuthentication;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private final SecurityCache securityCache;

    public SecurityService(SecurityCache securityCache) {
        this.securityCache = securityCache;
    }

    public String getSecurityToken(String login, String password) throws UserSecurityTokenException {
        try {
            return securityCache.loginUser(login, password);
        } catch (UserSecurityTokenException ex) {
            throw ex;
        }
    }

    @Nullable
    public UserAuthentication getUserByToken(@Nullable String token) {
        return securityCache.getUserByToken(token);
    }
}
