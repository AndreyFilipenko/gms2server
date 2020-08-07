package com.facom.domain;

public class UserAvatar {
    private final String avatar_name;
    private final boolean sex;

    public UserAvatar(String name, boolean sex) {
        this.avatar_name = name;
        this.sex = sex;
    }

    public String getAvatar_name() {
        return avatar_name;
    }

    public boolean isSex() {
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
