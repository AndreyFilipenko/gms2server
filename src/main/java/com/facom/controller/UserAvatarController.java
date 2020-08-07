package com.facom.controller;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatar;
import com.facom.domain.UserSecurityTokenDto;
import com.facom.exception.UserSecurityTokenException;
import com.facom.service.UserAvatarService;
import com.facom.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.facom.domain.OperationStatus.SUCCESSFUL_OPERATION;

@Controller
@RequestMapping("/avatar")
public class UserAvatarController {

    private final UserAvatarService userAvatarService;

    public UserAvatarController(UserAvatarService userAvatarService) {
        this.userAvatarService = userAvatarService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<OperationStatus, UserAvatar>> getUserAvatar(@RequestBody UserSecurityTokenDto userSecurityTokenDto) {
        String userLogin = SecurityUtils.getCurrentUserLogin();
        Long userId = SecurityUtils.getCurrentUserId();

        return ResponseEntity.ok(userAvatarService.getUserAvatar());
    }

}
