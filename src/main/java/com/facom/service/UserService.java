package com.facom.service;

import com.facom.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String,Object> createUser(String login, String password) {
        boolean isCreated = userRepository.createUser(login, password);
        Map jsonMap = new HashMap<>();
        if (isCreated) {
            jsonMap.put("operationStatus", "sucsess");
        } else {
            jsonMap.put("operationStatus", "failure");
        }
        return jsonMap;
    }
}
