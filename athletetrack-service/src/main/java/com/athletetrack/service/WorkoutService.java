package com.athletetrack.service;

import com.athletetrack.DTO.UserDTO;
import com.athletetrack.DTO.WorkoutDTO;
import com.athletetrack.entity.UserEntity;
import com.athletetrack.entity.WorkoutEntity;
import com.athletetrack.repository.WorkoutRepository;
import com.athletetrack.security.PasswordUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    private WorkoutDTO mapToDTO(WorkoutEntity workout) {
        return new WorkoutDTO(
                workout.getId(),
                workout.getAthleteId(),
                workout.getWorkoutType(),
                workout.getDescription(),
                workout.getDoneAt()
        );
    }

    public List<WorkoutDTO> getWorkoutsByUserId(Long userId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<WorkoutEntity> workouts = workoutRepository.getWorkoutsByUserId(userId, startDate, endDate);
        List<WorkoutDTO> workoutDTOs = new ArrayList<>();

        for (WorkoutEntity workout : workouts) {
            WorkoutDTO dto = mapToDTO(workout);
            workoutDTOs.add(dto);
        }

        return workoutDTOs;
    }

    public List<WorkoutDTO> getWorkoutsByCoachId(Long coachId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<WorkoutEntity> workouts = workoutRepository.getWorkoutsByCoachId(coachId, startDate, endDate);
        List<WorkoutDTO> workoutDTOs = new ArrayList<>();

        for (WorkoutEntity workout : workouts) {
            WorkoutDTO dto = mapToDTO(workout);
            workoutDTOs.add(dto);
        }

        return workoutDTOs;
    }

    public List<WorkoutDTO> getWorkoutsByClubName(String clubName, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<WorkoutEntity> workouts = workoutRepository.getWorkoutsByClubName(clubName, startDate, endDate);
        List<WorkoutDTO> workoutDTOs = new ArrayList<>();

        for (WorkoutEntity workout : workouts) {
            WorkoutDTO dto = mapToDTO(workout);
            workoutDTOs.add(dto);
        }

        return workoutDTOs;
    }

    public void saveWorkout(WorkoutDTO workoutDTO) throws SQLException {
        WorkoutEntity workoutEntity = new WorkoutEntity(
                workoutDTO.getAthleteId(),
                workoutDTO.getWorkoutType(),
                workoutDTO.getDescription(),
                workoutDTO.getDoneAt()
        );
        workoutRepository.saveWorkout(workoutEntity);
    }

}
