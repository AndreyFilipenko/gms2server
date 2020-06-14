package com.facom.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private static final String SQL_INSERT_USER = "insert into users (login, password) values (:login, :password);";

    private final NamedParameterJdbcTemplate template;

    public UserRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean createUser(String login, String password) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("login", login);
        namedParameters.addValue("password", password);
        try {
            int result = template.update(SQL_INSERT_USER, namedParameters);
            return result > 0;
        } catch (DataAccessException ex) {
            logger.error("Invoke createUser({},{}) with exception.", login, password, ex);
        }
        return false;
    }
}
