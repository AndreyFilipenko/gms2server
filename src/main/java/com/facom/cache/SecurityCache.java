package com.facom.cache;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.facom.domain.UserOperationStatus;
import com.facom.exception.UserSecurityTokenException;
import com.facom.model.UserEnvironment;
import com.facom.model.security.UserAuthentication;
import com.facom.repository.SecurityRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.facom.domain.UserOperationStatus.*;

@Component
public class SecurityCache {
    private final SecurityRepository securityRepository;
    private final Map<String, UserAuthentication> usersTokens = new ConcurrentHashMap<>();

    public SecurityCache(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public String loginUser(String login, String password) throws UserSecurityTokenException {
        String securityToken = UUID.randomUUID().toString();

        Long id = securityRepository.verifyUserLogin(login, password);
        if (id != null) {
            UserEnvironment environment = new UserEnvironment(id, login, password);
            usersTokens.put(securityToken, new UserAuthentication(environment));
        } else {
            throw new UserSecurityTokenException(FAILED_AUTHORIZATION);
        }

        return securityToken;
    }

    @Nullable
    public UserAuthentication getUserByToken(@Nullable String token) {
        return token == null ? null : usersTokens.get(token);
    }
}
