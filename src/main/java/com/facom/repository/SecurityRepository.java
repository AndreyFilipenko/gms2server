package com.facom.repository;

import com.facom.exception.UserSecurityTokenException;
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

import static com.facom.domain.UserOperationStatus.*;


@Repository
public class SecurityRepository {
    private static final Logger logger = LoggerFactory.getLogger(SecurityRepository.class);

    private final NamedParameterJdbcTemplate template;

    private static final String SQL_SELECT_VERIFIED_UID = "select id from users where password = :password and login = :login;";

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
}
