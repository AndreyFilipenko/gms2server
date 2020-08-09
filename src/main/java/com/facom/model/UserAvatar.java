package com.facom.model;

import com.facom.domain.UserAvatarSex;

public class UserAvatar {
    private final String avatar_name;
    private final UserAvatarSex sex;

    public UserAvatar(String name, String sex) {
        this.avatar_name = name;
        switch (sex) {
            case "FEMALE": {
                this.sex = UserAvatarSex.FEMALE;
                break;
            }
            default:  {
                this.sex = UserAvatarSex.MALE;
                break;
            }
        }
    }

    public String getAvatar_name() {
        return avatar_name;
    }

    public UserAvatarSex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "UserAvatar{" +
                ", name='" + avatar_name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
