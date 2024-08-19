package com.athletetrack.repository;

import com.athletetrack.entity.UserEntity;
import com.athletetrack.entity.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public List<UserEntity> getAllUsers() throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        String sql = "SELECT * FROM public.users";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapToUserEntity(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
            throw e;
        }

        return users;
    }

    private UserEntity mapToUserEntity(ResultSet rs) throws SQLException {
        Long coachId = rs.getObject("coach_id") != null ? rs.getLong("coach_id") : null;
        UserType userType = UserType.valueOf(rs.getString("user_type"));

        return new UserEntity(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("phone"),
                userType,
                coachId,
                rs.getString("sport"),
                rs.getString("club"),
                rs.getTimestamp("created_at").toInstant()
        );
    }

}
