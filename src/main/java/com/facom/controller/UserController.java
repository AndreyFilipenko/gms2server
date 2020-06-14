package com.facom.controller;

import com.facom.domain.UserCreateDto;
import com.facom.service.UserService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDto userCreateDto) {
        JSONObject jsonResult = userService.createUser(userCreateDto.getLogin(),
                userCreateDto.getPassword());
        return ResponseEntity.ok(jsonResult.toString());
    }
}
