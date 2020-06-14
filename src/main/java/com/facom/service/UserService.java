package com.facom.service;

import com.facom.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JSONObject createUser(String login, String password) {
        boolean isCreated = userRepository.createUser(login, password);
        JSONObject result = new JSONObject();
        if (isCreated) {
            result.put("operationStatus", "sucsess");
        } else {
            result.put("operationStatus", "failure");
        }
        return result;
    }
}
