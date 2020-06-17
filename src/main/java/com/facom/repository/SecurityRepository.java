package com.facom.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.UUID;

@Repository
public class SecurityRepository {
    private static final Logger logger = LoggerFactory.getLogger(SecurityRepository.class);

    private final NamedParameterJdbcTemplate template;

    private static final String SQL_SELECT_VERIFIED_UID = "select id from users where password = :password and login = :login;";
    private static final String SQL_SELECT_TOKEN_BY_UID = "select token from login_tokens where user_id = :user_id;";
    private static final String SQL_INSERT_NEW_TOKEN = "insert into login_tokens (user_id, token) values (:user_id, :security_token);";

    public SecurityRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Nullable
    public Long verifyUserLogin(String login, String password) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("login", login);
        namedParameters.addValue("password", password);
        try {
            return template.queryForObject(SQL_SELECT_VERIFIED_UID, namedParameters, Long.class);
        } catch (EmptyResultDataAccessException ex) {
            logger.error("Invoke verifyUserLogin({},{}) with exception.", login, password, ex);
        }
        return null;
    }

    @Nullable
    public String getSecurityToken(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", userId);
        try {
            return template.queryForObject(SQL_SELECT_TOKEN_BY_UID, namedParameters, String.class);
        } catch (EmptyResultDataAccessException ex) {
            logger.error("Invoke getSecurityToken({}) with exception.", userId, ex);
        }
        return null;
    }

    @Nullable
    public Integer generateSecurityToken(Long userId) {
        String securityToken = UUID.randomUUID().toString();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", userId);
        namedParameters.addValue("security_token", securityToken);
        try {
            return template.update(SQL_INSERT_NEW_TOKEN, namedParameters);
        } catch (DataAccessException ex) {
            logger.error("Invoke generateSecurityToken({}) with exception.", userId, ex);
        }
    return null;
    }
}
