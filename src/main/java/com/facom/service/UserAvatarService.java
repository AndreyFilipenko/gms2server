package com.facom.service;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatarSex;
import com.facom.domain.UserAvatar;
import com.facom.repository.UserAvatarRepository;
import org.springframework.stereotype.Service;

import static com.facom.domain.OperationStatus.*;

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

    public OperationStatus updateUserAvatar(String avatarName, UserAvatarSex avatarSex) {
        Integer result = userAvatarRepository.updateUserAvatar(avatarName, avatarSex);
        if (result != null) {
            return result > 0 ? SUCCESSFUL_OPERATION : USER_AVATAR_NOT_EXISTS;
        } else {
            return INTERNAL_ERROR;
        }
    }

 }
