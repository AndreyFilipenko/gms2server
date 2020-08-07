package com.facom.service;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatar;
import com.facom.repository.UserAvatarRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.facom.domain.OperationStatus.FAILED_OPERATION;
import static com.facom.domain.OperationStatus.SUCCESSFUL_OPERATION;

@Service
public class UserAvatarService {
    private final UserAvatarRepository userAvatarRepository;

    public UserAvatarService(UserAvatarRepository repository) {
        this.userAvatarRepository = repository;
    }

    public ApiResponse<OperationStatus, UserAvatar> getUserAvatar() {
        ApiResponse<OperationStatus, UserAvatar> userAvatar = userAvatarRepository.getUserAvatar();
        return userAvatar;
    }
}
