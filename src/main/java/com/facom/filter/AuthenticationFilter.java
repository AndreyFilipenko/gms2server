package com.facom.filter;

import com.facom.cache.SecurityCache;
import com.facom.model.security.UserAuthentication;
import com.facom.service.SecurityService;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter extends GenericFilterBean {
    private final SecurityService securityService;
    private final Set<String> excludedUrls;

    public AuthenticationFilter(SecurityService securityService, String[] excludedUrls) {
        this.securityService = securityService;
        this.excludedUrls = excludedUrls == null ? Collections.emptySet() : new HashSet<>(Arrays.asList(excludedUrls));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (excludedUrls.contains(httpRequest.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (!"OPTIONS".equals(httpRequest.getMethod())) {
            String securityToken = httpRequest.getHeader("token");

            if (securityToken != null) {
                UserAuthentication authentication = securityService.getUserByToken(securityToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token!");
                    return;
                }
            } else {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token is empty!");
                SecurityContextHolder.getContext().setAuthentication(null);
                return;
            }
        }

    filterChain.doFilter(servletRequest, servletResponse);
    }
}

