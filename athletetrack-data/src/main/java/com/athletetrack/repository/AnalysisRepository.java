package com.athletetrack.repository;

import com.athletetrack.entity.AnalysisEntity;
import com.athletetrack.entity.WorkoutEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    WorkoutRepository workoutRepository = new WorkoutRepository();

    public AnalysisEntity getAnalysisForAthlete(long athleteId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        AnalysisEntity analysis = new AnalysisEntity();

        String totalWorkoutsQuery = "SELECT COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(totalWorkoutsQuery)) {
            ps.setLong(1, athleteId);
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    analysis.setTotalWorkouts(rs.getInt("total"));
                }
            }
        }

        String totalWorkoutsByTypeQuery = "SELECT workout_type, COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ? GROUP BY workout_type";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(totalWorkoutsByTypeQuery)) {
            ps.setLong(1, athleteId);
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));
            try (ResultSet rs = ps.executeQuery()) {
                Map<String, Integer> totalWorkoutsByType = new HashMap<>();
                while (rs.next()) {
                    totalWorkoutsByType.put(rs.getString("workout_type"), rs.getInt("total"));
                }
                analysis.setTotalWorkoutsByType(totalWorkoutsByType);
            }
        }

        analysis.setWorkouts(workoutRepository.getWorkoutsByUserId(athleteId, startDate, endDate));


        return analysis;
    }

    public List<AnalysisEntity> getAnalysisByCoachId(Long coachId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<AnalysisEntity> analysisList = new ArrayList<>();

        String athletesQuery = "SELECT DISTINCT athlete_id FROM workouts WHERE athlete_id IN (SELECT id FROM users WHERE coach_id = ?)";

        try (Connection conn = getConnection();
             PreparedStatement psAthletes = conn.prepareStatement(athletesQuery)) {
            psAthletes.setLong(1, coachId);

            try (ResultSet rsAthletes = psAthletes.executeQuery()) {
                while (rsAthletes.next()) {
                    Long athleteId = rsAthletes.getLong("athlete_id");

                    AnalysisEntity analysisEntity = new AnalysisEntity();

                    // Извличане на общ брой тренировки за даден атлет в зададения период
                    String totalWorkoutsQuery = "SELECT COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ?";
                    try (PreparedStatement psTotal = conn.prepareStatement(totalWorkoutsQuery)) {
                        psTotal.setLong(1, athleteId);
                        psTotal.setTimestamp(2, Timestamp.valueOf(startDate));
                        psTotal.setTimestamp(3, Timestamp.valueOf(endDate));
                        try (ResultSet rsTotal = psTotal.executeQuery()) {
                            if (rsTotal.next()) {
                                analysisEntity.setTotalWorkouts(rsTotal.getInt("total"));
                            }
                        }
                    }

                    String totalWorkoutsByTypeQuery = "SELECT workout_type, COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ? GROUP BY workout_type";
                    try (PreparedStatement psType = conn.prepareStatement(totalWorkoutsByTypeQuery)) {
                        psType.setLong(1, athleteId);
                        psType.setTimestamp(2, Timestamp.valueOf(startDate));
                        psType.setTimestamp(3, Timestamp.valueOf(endDate));
                        try (ResultSet rsType = psType.executeQuery()) {
                            Map<String, Integer> totalWorkoutsByType = new HashMap<>();
                            while (rsType.next()) {
                                totalWorkoutsByType.put(rsType.getString("workout_type"), rsType.getInt("total"));
                            }
                            analysisEntity.setTotalWorkoutsByType(totalWorkoutsByType);
                        }
                    }

                    List<WorkoutEntity> workouts = workoutRepository.getWorkoutsByUserId(athleteId, startDate, endDate);
                    analysisEntity.setWorkouts(workouts);

                    analysisList.add(analysisEntity);
                }
            }
        }

        return analysisList;
    }

    public List<AnalysisEntity> getAnalysisByClub(String clubName, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<AnalysisEntity> analysisList = new ArrayList<>();

        String athletesQuery = "SELECT DISTINCT athlete_id FROM workouts WHERE athlete_id IN (SELECT id FROM users WHERE club = ?)";

        try (Connection conn = getConnection();
             PreparedStatement psAthletes = conn.prepareStatement(athletesQuery)) {
            psAthletes.setString(1, clubName);

            try (ResultSet rsAthletes = psAthletes.executeQuery()) {
                while (rsAthletes.next()) {
                    Long athleteId = rsAthletes.getLong("athlete_id");

                    AnalysisEntity analysisEntity = new AnalysisEntity();

                    String totalWorkoutsQuery = "SELECT COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ?";
                    try (PreparedStatement psTotal = conn.prepareStatement(totalWorkoutsQuery)) {
                        psTotal.setLong(1, athleteId);
                        psTotal.setTimestamp(2, Timestamp.valueOf(startDate));
                        psTotal.setTimestamp(3, Timestamp.valueOf(endDate));
                        try (ResultSet rsTotal = psTotal.executeQuery()) {
                            if (rsTotal.next()) {
                                analysisEntity.setTotalWorkouts(rsTotal.getInt("total"));
                            }
                        }
                    }

                    String totalWorkoutsByTypeQuery = "SELECT workout_type, COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ? GROUP BY workout_type";
                    try (PreparedStatement psType = conn.prepareStatement(totalWorkoutsByTypeQuery)) {
                        psType.setLong(1, athleteId);
                        psType.setTimestamp(2, Timestamp.valueOf(startDate));
                        psType.setTimestamp(3, Timestamp.valueOf(endDate));
                        try (ResultSet rsType = psType.executeQuery()) {
                            Map<String, Integer> totalWorkoutsByType = new HashMap<>();
                            while (rsType.next()) {
                                totalWorkoutsByType.put(rsType.getString("workout_type"), rsType.getInt("total"));
                            }
                            analysisEntity.setTotalWorkoutsByType(totalWorkoutsByType);
                        }
                    }

                    List<WorkoutEntity> workouts = workoutRepository.getWorkoutsByUserId(athleteId, startDate, endDate);
                    analysisEntity.setWorkouts(workouts);

                    analysisList.add(analysisEntity);
                }
            }
        }

        return analysisList;
    }
}
