package com.facom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAvatarDto {
    private final String avatar_name;
    private final UserAvatarSex sex;

    @JsonCreator
    public UserAvatarDto(@JsonProperty("avatar_name") String avatar_name,
                         @JsonProperty("avatar_sex") String sex) {
        this.avatar_name = avatar_name;
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
        return "UserAvatarDto{" +
                "avatar_name='" + avatar_name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
