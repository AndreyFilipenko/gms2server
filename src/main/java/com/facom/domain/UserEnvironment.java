package com.facom.domain;

public class UserEnvironment {
    private final Long id;
    private final String login;
    private final String password;

    public UserEnvironment(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }
}
