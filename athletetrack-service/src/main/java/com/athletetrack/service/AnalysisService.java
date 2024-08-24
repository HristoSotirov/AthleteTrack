package com.athletetrack.service;

import com.athletetrack.DTO.AnalysisDTO;
import com.athletetrack.DTO.WorkoutDTO;
import com.athletetrack.entity.AnalysisEntity;
import com.athletetrack.entity.WorkoutEntity;
import com.athletetrack.repository.AnalysisRepository;
import com.athletetrack.repository.WorkoutRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnalysisService {

    private final AnalysisRepository analysisRepository;

    public AnalysisService(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    private AnalysisDTO mapToDTO(AnalysisEntity analysis) {
        return new AnalysisDTO(
                analysis.getTotalWorkouts(),
                analysis.getTotalWorkoutsByType(),
                analysis.getWorkouts()
        );
    }

    public AnalysisDTO getAnalysisForAthlete(Long userId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        AnalysisEntity analysisEntity = analysisRepository.getAnalysisForAthlete(userId, startDate, endDate);
        AnalysisDTO analysisDTO = mapToDTO(analysisEntity);

        return analysisDTO;
    }

    public List<AnalysisDTO> getAnalysisByCoach(Long coachId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<AnalysisEntity> analysisEntities = analysisRepository.getAnalysisByCoachId(coachId, startDate, endDate);

        List<AnalysisDTO> analysisDTOs = new ArrayList<>();

        for (AnalysisEntity analysisEntity : analysisEntities) {
            AnalysisDTO analysisDTO = mapToDTO(analysisEntity);
            analysisDTOs.add(analysisDTO);
        }

        return analysisDTOs;
    }

    public List<AnalysisDTO> getAnalysisByClub(String clubName, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<AnalysisEntity> analysisEntities = analysisRepository.getAnalysisByClub(clubName, startDate, endDate);

        List<AnalysisDTO> analysisDTOs = new ArrayList<>();

        for (AnalysisEntity analysisEntity : analysisEntities) {
            AnalysisDTO analysisDTO = mapToDTO(analysisEntity);
            analysisDTOs.add(analysisDTO);
        }

        return analysisDTOs;
    }
}
