package com.facom.service;

import com.facom.domain.ApiResponse;
import com.facom.domain.UserOperationStatus;
import com.facom.exception.UserSecurityTokenException;
import com.facom.repository.SecurityRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityService {
    private final SecurityRepository securityRepository;

    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public Map<String, Object> login(String login, String password) {
        Long verifiedUserId = securityRepository.verifyUserLogin(login, password);
        Map jsonMap = new HashMap<>();
        if (verifiedUserId != null) {
            String securityToken = securityRepository.getSecurityToken(verifiedUserId);
            if (securityToken != null) {
                jsonMap.put("securityToken", securityToken);
            } else {
                Integer result = securityRepository.generateSecurityToken(verifiedUserId);
                if (result != null) {
                    securityToken = securityRepository.getSecurityToken(verifiedUserId);
                    if (securityToken != null) {
                        jsonMap.put("securityToken", securityToken);
                    }
                }
            }
        }
        if (jsonMap.containsKey("securityToken")) {
            jsonMap.put("operationStatus", "success");
        } else {
            jsonMap.put("operationStatus", "wrong_login");
        }
        return jsonMap;
    }

    //Return null if no user for this token
    @Nullable
    public Long getUserIdBySecurityToken (String securityToken) throws UserSecurityTokenException {
        try {
            return securityRepository.getUserIdByToken(securityToken);
        } catch (UserSecurityTokenException ex) {
            throw ex;
        }
    }
}
