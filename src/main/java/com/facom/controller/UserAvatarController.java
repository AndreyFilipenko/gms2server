package com.facom.controller;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatarDto;
import com.facom.domain.UserAvatar;
import com.facom.service.UserAvatarService;
import com.facom.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.facom.domain.OperationStatus.*;

@Controller
@RequestMapping("/avatar")
public class UserAvatarController {

    private final UserAvatarService userAvatarService;

    public UserAvatarController(UserAvatarService userAvatarService) {
        this.userAvatarService = userAvatarService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<OperationStatus, UserAvatar>> getUserAvatar() {
        String userLogin = SecurityUtils.getCurrentUserLogin();
        Long userId = SecurityUtils.getCurrentUserId();

        return ResponseEntity.ok(userAvatarService.getUserAvatar());
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OperationStatus,Object>> createUserAvatar(@RequestBody UserAvatarDto userAvatarDto) {
        boolean result = userAvatarService.createUserAvatar(userAvatarDto.getAvatar_name(), userAvatarDto.getSex());
        if (result) {
            return ResponseEntity.ok(new ApiResponse<>(OperationStatus.SUCCESSFUL_OPERATION, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(FAILED_OPERATION, null));

    }

}
