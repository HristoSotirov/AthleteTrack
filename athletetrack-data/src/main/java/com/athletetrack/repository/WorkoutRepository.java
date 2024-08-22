package com.athletetrack.repository;

import com.athletetrack.entity.UserEntity;
import com.athletetrack.entity.WorkoutEntity;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    private WorkoutEntity mapToWorkoutEntity(ResultSet rs) throws SQLException {
        return new WorkoutEntity(
                rs.getLong("id"),
                rs.getLong("athlete_id"),
                rs.getString("workout_type"),
                rs.getString("description"),
                rs.getTimestamp("done_at").toLocalDateTime()
        );
    }

    public List<WorkoutEntity> getWorkoutsByUserId(Long userId) throws SQLException {
        List<WorkoutEntity> workouts = new ArrayList<>();

        String query = "SELECT * FROM workouts WHERE athlete_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                WorkoutEntity workout = mapToWorkoutEntity(rs);

                workouts.add(workout);
            }
        }

        return workouts;
    }

    public List<WorkoutEntity> getWorkoutsByCoachId(Long coachId) throws SQLException {
        List<WorkoutEntity> workouts = new ArrayList<>();

        String query = "SELECT w.* FROM workouts w JOIN users u ON w.athlete_id = u.id WHERE u.coach_id = ? ORDER BY w.athlete_id, w.done_at";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, coachId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                WorkoutEntity workout = mapToWorkoutEntity(rs);
                workouts.add(workout);
            }
        }

        return workouts;
    }

    public List<WorkoutEntity> getWorkoutsByClubName(String clubName) throws SQLException {
        List<WorkoutEntity> workouts = new ArrayList<>();

        String query = "SELECT w.* FROM workouts w JOIN users u ON w.athlete_id = u.id WHERE u.club = ? ORDER BY w.athlete_id, w.done_at";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, clubName);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                WorkoutEntity workout = mapToWorkoutEntity(rs);
                workouts.add(workout);
            }
        }

        return workouts;
    }


    public void saveWorkout(WorkoutEntity workout) throws SQLException {
        String sql = "INSERT INTO public.workouts (athlete_id, workout_type, description, done_at) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, workout.getAthleteId());
            pstmt.setString(2, workout.getWorkoutType());
            pstmt.setString(3, workout.getDescription());
            ZonedDateTime zonedDateTime = workout.getDoneAt().atZone(ZoneId.systemDefault());
            pstmt.setTimestamp(4, Timestamp.from(zonedDateTime.toInstant()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0 && workout.getId() == null) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        workout.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving workout: " + e.getMessage());
            throw e;
        }
    }
}
