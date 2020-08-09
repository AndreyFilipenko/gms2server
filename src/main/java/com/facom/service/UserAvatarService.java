package com.facom.service;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatarSex;
import com.facom.model.UserAvatar;
import com.facom.repository.UserAvatarRepository;
import org.springframework.stereotype.Service;

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

    public boolean createUserAvatar(String avatarName, UserAvatarSex avatarSex) {
        return userAvatarRepository.createUserAvatar(avatarName, avatarSex);
    }
 }
