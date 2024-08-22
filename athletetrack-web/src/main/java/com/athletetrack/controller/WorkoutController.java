package com.athletetrack.controller;

import com.athletetrack.DTO.UserDTO;
import com.athletetrack.DTO.WorkoutDTO;
import com.athletetrack.service.UserService;
import com.athletetrack.service.WorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class WorkoutController {
    private final WorkoutService workoutService;
    private final ObjectMapper objectMapper;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public String getWorkoutsByUserId(String id) {
        try {
            List<WorkoutDTO> workouts = workoutService.getWorkoutsByUserId(Long.parseLong(id));
            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(workouts);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String getWorkoutsByCoachId(String coachId) {
        try {
            List<WorkoutDTO> workouts = workoutService.getWorkoutsByCoachId(Long.parseLong(coachId));
            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(workouts);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String getWorkoutsByClubName(String clubName) {
        try {
            List<WorkoutDTO> workouts = workoutService.getWorkoutsByClubName(clubName);
            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(workouts);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String saveWorkout(Long athleteId, String workoutType, String description, LocalDateTime doneAt) {
        try {
            WorkoutDTO workoutDTO = new WorkoutDTO(athleteId, workoutType, description, doneAt);
            workoutService.saveWorkout(workoutDTO);
            return "HTTP/1.1 201 Created\r\n\r\n";
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }
}
