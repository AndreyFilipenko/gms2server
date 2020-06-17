package com.facom.service;

import com.facom.repository.SecurityRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private final SecurityRepository securityRepository;

    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public JSONObject login(String login, String password) {
        Long verifiedUserId = securityRepository.verifyUserLogin(login, password);
        JSONObject json = new JSONObject();
        if (verifiedUserId != null) {
            String securityToken = securityRepository.getSecurityToken(verifiedUserId);
            if (securityToken != null) {
                json.put("securityToken", securityToken);
                json.put("operationStatus", "success");
            } else {
                Integer result = securityRepository.generateSecurityToken(verifiedUserId);
                if (result != null) {
                    securityToken = securityRepository.getSecurityToken(verifiedUserId);
                    if (securityToken != null) {
                        json.put("securityToken", securityToken);
                        json.put("operationStatus", "success");
                    } else {
                        json.put("operationStatus", "wrong_login");
                    }
                } else {
                    json.put("operationStatus", "wrong_login");
                }
            }
        } else {
            json.put("operationStatus", "wrong_login");
        }
        return json;
    }
}
