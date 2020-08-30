package com.facom.domain.security;

import com.facom.domain.UserEnvironment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {
    private final UserEnvironment userEnvironment;
    private boolean isAuthenticated;

    public UserAuthentication(UserEnvironment userEnvironment) {
        this.userEnvironment = userEnvironment;
        this.isAuthenticated = true;
    }

    public UserEnvironment getUserEnvironment() {
        return userEnvironment;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.isAuthenticated = b;
    }

    @Override
    public String getName() {
        return null;
    }
}
