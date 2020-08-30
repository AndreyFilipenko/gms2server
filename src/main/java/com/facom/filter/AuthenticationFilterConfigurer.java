package com.facom.filter;

import com.facom.service.SecurityService;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final SecurityService securityService;
    @Nullable
    private final String[] excludedUrls;

    public AuthenticationFilterConfigurer(SecurityService securityService, @Nullable String... excludedUrls) {
        this.securityService = securityService;
        this.excludedUrls = excludedUrls;
    }

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationFilter filter = new AuthenticationFilter(securityService, excludedUrls);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
