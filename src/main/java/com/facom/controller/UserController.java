package com.facom.controller;

import com.facom.domain.*;
import com.facom.exception.UserSecurityTokenException;
import com.facom.service.SecurityService;
import com.facom.service.UserService;
import com.facom.utils.SecurityUtils;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.facom.domain.UserOperationStatus.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    final private UserService userService;
    final private SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/reg")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserCreateDto userCreateDto) {
        Map jsonMapResult = userService.createUser(userCreateDto.getLogin(),
                userCreateDto.getPassword());
        return ResponseEntity.ok(jsonMapResult);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserOperationStatus, Map<String, Object>>> login(@RequestBody UserLoginDto userLoginDto) throws UserSecurityTokenException {
        try {
            String securityToken = securityService.getSecurityToken(userLoginDto.getLogin(),
                    userLoginDto.getPassword());
            Map<String, Object> responseJsonMap = new HashMap<>();
            responseJsonMap.put("security_token", securityToken);
            return ResponseEntity.ok(new ApiResponse<>(SUCCESSFUL_OPERATION, responseJsonMap));
        } catch (UserSecurityTokenException ex) {
            UserOperationStatus operationStatus = ex.getOperationStatus();
            return ResponseEntity.ok(new ApiResponse(operationStatus, null));
        }
    }

    @PostMapping("/avatar")
    public ResponseEntity<ApiResponse<UserOperationStatus, Map<String, Object>>> getUserAvatar(@RequestBody UserSecurityTokenDto userSecurityTokenDto) {
        String userLogin = SecurityUtils.getCurrentUserLogin();
        System.out.println("Current User: " + userLogin);

        return ResponseEntity.ok(new ApiResponse(SUCCESSFUL_OPERATION, null));
        /*
        try {
            Long userId = securityService.getUserIdBySecurityToken(userSecurityTokenDto.getSecurityToken());;
            Map<String, Object> responseJsonMap = new HashMap<>();
            responseJsonMap.put("userId", userId);
            return ResponseEntity.ok(new ApiResponse<>(SUCCESSFUL_OPERATION, responseJsonMap));
        } catch (UserSecurityTokenException ex) {
            UserOperationStatus operationStatus = ex.getOperationStatus();
            return ResponseEntity.ok(new ApiResponse(operationStatus, null));
        }*/
    }
}
