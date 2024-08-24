package com.athletetrack.DTO;

import com.athletetrack.entity.WorkoutEntity;

import java.util.List;
import java.util.Map;

public class AnalysisDTO {
    private int totalWorkouts;
    private Map<String, Integer> totalWorkoutsByType;
    private List<WorkoutEntity> workouts;

    public AnalysisDTO() {
    }

    public AnalysisDTO(int totalWorkouts, Map<String, Integer> totalWorkoutsByType, List<WorkoutEntity> workouts) {
        this.totalWorkouts = totalWorkouts;
        this.totalWorkoutsByType = totalWorkoutsByType;
        this.workouts = workouts;
    }

    public int getTotalWorkouts() {
        return totalWorkouts;
    }

    public void setTotalWorkouts(int totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }

    public Map<String, Integer> getTotalWorkoutsByType() {
        return totalWorkoutsByType;
    }

    public void setTotalWorkoutsByType(Map<String, Integer> totalWorkoutsByType) {
        this.totalWorkoutsByType = totalWorkoutsByType;
    }

    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }
}
