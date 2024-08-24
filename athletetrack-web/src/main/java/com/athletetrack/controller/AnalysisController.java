package com.athletetrack.controller;

import com.athletetrack.DTO.AnalysisDTO;
import com.athletetrack.DTO.WorkoutDTO;
import com.athletetrack.service.AnalysisService;
import com.athletetrack.service.WorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AnalysisController {

    private final AnalysisService analysisService;
    private final ObjectMapper objectMapper;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public String getAnalysisForAthlete(String athleteID, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            AnalysisDTO analysisDTO = analysisService.getAnalysisForAthlete(Long.parseLong(athleteID), startDate, endDate);
            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(analysisDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String getAnalysisByCoach(String coachId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            List<AnalysisDTO> analysisDTOList = analysisService.getAnalysisByCoach(Long.parseLong(coachId), startDate, endDate);

            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(analysisDTOList);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String getAnalysisByClub(String clubName, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            List<AnalysisDTO> analysisDTOList = analysisService.getAnalysisByClub(clubName, startDate, endDate);

            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(analysisDTOList);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

}
