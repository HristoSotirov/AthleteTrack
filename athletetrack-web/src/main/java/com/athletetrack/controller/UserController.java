package com.athletetrack.controller;

import com.athletetrack.DTO.UserDTO;
import com.athletetrack.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public String getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(users);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String getUserById(String id) {
        try {
            UserDTO user = userService.getUserById(Long.parseLong(id));

            if (user == null) {
                return "HTTP/1.1 404 Not Found\r\n\r\n";
            }

            return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + objectMapper.writeValueAsString(user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "HTTP/1.1 400 Bad Request\r\n\r\n";
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String registerCoach(String name, String username, String password, String email, String phone, String sport, String club) {
        try {
            UserDTO userDTO = new UserDTO(name, username, password, email, phone, sport, club);
            userService.registerCoach(userDTO);
            return "HTTP/1.1 201 Created\r\n\r\n";
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String registerAthlete(Long coachId, String name, String username, String password, String email, String phone) {
        try {
            UserDTO userDTO = new UserDTO(name, username, password, email, phone);
            userService.registerAthlete(coachId, userDTO);
            return "HTTP/1.1 201 Created\r\n\r\n";
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    public String login(String username, String password) {
        try {
            UserDTO userDTO = new UserDTO(username, password);
            String jwtToken = userService.login(userDTO);
            return "HTTP/1.1 201 Created\r\n\r\n" + objectMapper.writeValueAsString(jwtToken);
        } catch (SQLException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }
}
