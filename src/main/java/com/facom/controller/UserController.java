package com.facom.controller;

import com.facom.domain.UserCreateDto;
import com.facom.domain.UserLoginDto;
import com.facom.service.SecurityService;
import com.facom.service.UserService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    final private UserService userService;
    final private SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/reg")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDto userCreateDto) {
        JSONObject jsonResult = userService.createUser(userCreateDto.getLogin(),
                userCreateDto.getPassword());
        return ResponseEntity.ok(jsonResult.toString());
    }
    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {

        JSONObject jsonResult = securityService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        return ResponseEntity.ok(jsonResult.toString());
    }
}
