package com.facom.repository;

import com.facom.domain.ApiResponse;
import com.facom.domain.OperationStatus;
import com.facom.domain.UserAvatarSex;
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

    private static final String INSERT_USER_AVATAR = "insert into users_avatar(user_id, avatar_name, sex) values (:user_id, :avatar_name, :sex)";

    private static final String UPDATE_USER_AVATAR = "update users_avatar set (avatar_name, sex) = (:avatar_name, :sex) where user_id = :user_id;";

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

    public boolean createUserAvatar(String avatarName, UserAvatarSex avatarSex) {
        OperationStatus userAvatarStatus = getUserAvatar().getOperationStatus();
        if (userAvatarStatus == USER_AVATAR_NOT_EXISTS) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("user_id", SecurityUtils.getCurrentUserId());
            namedParameters.addValue("avatar_name", avatarName);
            namedParameters.addValue("sex", avatarSex.toString());
            try {
                int result = template.update(INSERT_USER_AVATAR, namedParameters);
                return result > 0;
            } catch (DataAccessException ex) {
                logger.error("Invoke createUserAvatar({},{}) with exception.", avatarName, avatarSex, ex);
            }
        }
        return false;
    }

    public Integer updateUserAvatar(String avatarName, UserAvatarSex avatarSex) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", SecurityUtils.getCurrentUserId());
        namedParameters.addValue("avatar_name", avatarName);
        namedParameters.addValue("sex", avatarSex.toString());
        try {
            int result = template.update(UPDATE_USER_AVATAR, namedParameters);
            return result;
        } catch (DataAccessException ex) {
            logger.error("Invoke updateUserAvatar({},{}) with exception.", avatarName, avatarSex, ex);
        }
        return null;
    }

    private static RowMapper<UserAvatar>  getUserAvatarRowMapper() {
        return (rs, i) -> new UserAvatar(
                rs.getString("avatar_name"),
                rs.getString("sex")
        );
    }
}
