package com.facom.repository;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatar;
import com.facom.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import static com.facom.domain.OperationStatus.*;

@Repository
public class UserAvatarRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserAvatarRepository.class);

    private static final String SELECT_USER_AVATAR = "select * from users_avatar where user_id = :user_id;";

    private final NamedParameterJdbcTemplate template;

    public UserAvatarRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public ApiResponse<OperationStatus, UserAvatar> getUserAvatar() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", SecurityUtils.getCurrentUserId());
        try {
            UserAvatar result = template.queryForObject(SELECT_USER_AVATAR, namedParameters,getUserAvatarRowMapper());
            if (result != null) {
                return new ApiResponse<>(SUCCESSFUL_OPERATION, result);
            }
        } catch (EmptyResultDataAccessException ex) {
            return new ApiResponse<>(USER_AVATAR_NOT_EXISTS, null);
        } catch (DataAccessException ex) {
            logger.error("Invoke getUserAvatar() with exception.", ex);
        }
        return new ApiResponse<>(FAILED_OPERATION, null);

    }

    private static RowMapper<UserAvatar>  getUserAvatarRowMapper() {
        return (rs, i) -> new UserAvatar(
                rs.getString("avatar_name"),
                rs.getBoolean("sex")
        );
    }
}
