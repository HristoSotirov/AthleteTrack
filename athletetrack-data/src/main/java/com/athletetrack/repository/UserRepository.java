package com.athletetrack.repository;

import com.athletetrack.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    private UserEntity mapToUserEntity(ResultSet rs) throws SQLException {
        Long coachId = rs.getObject("coach_id") != null ? rs.getLong("coach_id") : null;
        UserEntity.UserType userType = UserEntity.UserType.valueOf(rs.getString("user_type").toUpperCase());

        return new UserEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("phone"),
                userType,
                coachId,
                null,
                rs.getString("sport"),
                rs.getString("club"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }

    public List<UserEntity> getAllUsers() throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        String sql = "SELECT * FROM public.users";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UserEntity user = mapToUserEntity(rs);
                if (user.getCoachId() != null) {
                    user.setCoachName(getCoachNameById(conn, user.getCoachId()));
                }
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
            throw e;
        }

        return users;
    }

    private String getCoachNameById(Connection conn, Long coachId) throws SQLException {
        String coachName = null;
        String sql = "SELECT name FROM public.users WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, coachId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    coachName = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching coach name: " + e.getMessage());
            throw e;
        }

        return coachName;
    }

    public UserEntity getUserById(Long userId) throws SQLException {
        UserEntity user = null;
        String sql = "SELECT * FROM public.users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = mapToUserEntity(rs);
                    if (user.getCoachId() != null) {
                        user.setCoachName(getCoachNameById(conn, user.getCoachId()));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user by ID: " + e.getMessage());
            throw e;
        }

        return user;
    }

    public void saveCoach(UserEntity user) throws SQLException {
        String sql = "INSERT INTO public.users (name, username, password, email, phone, user_type, sport, club) " +
                "VALUES (?, ?, ?, ?, ?, ?::user_type, ?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getUserType().name());
            pstmt.setString(7, user.getSport());
            pstmt.setString(8, user.getClub());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0 && user.getId() == null) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
            throw e;
        }
    }

    public void saveAthlete(Long coachID, UserEntity user) throws SQLException {
        String sql = "INSERT INTO public.users (name, username, password, email, phone, user_type, sport, club) " +
                "VALUES (?, ?, ?, ?, ?, ?::user_type, ?,?)";


        UserEntity coach = getUserById(coachID);

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {



            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getUserType().name());
            pstmt.setString(7, coach.getSport());
            pstmt.setString(8, coach.getClub());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0 && user.getId() == null) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
            throw e;
        }
    }

    public UserEntity login(UserEntity user) throws SQLException {
        String sql = "SELECT * FROM public.users WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserEntity loggedInUser = mapToUserEntity(rs);
                    System.out.println("Login successful for user: " + loggedInUser.getUsername());
                    return new UserEntity(loggedInUser.getId(), loggedInUser.getUsername());
                } else {
                    System.out.println("Login failed: Invalid username or password.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            throw e;
        }
    }



}
