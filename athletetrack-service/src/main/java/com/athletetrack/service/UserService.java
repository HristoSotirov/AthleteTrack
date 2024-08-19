package com.athletetrack.service;

import com.athletetrack.DTO.UserDTO;
import com.athletetrack.entity.UserEntity;
import com.athletetrack.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserEntity> users = userRepository.getAllUsers();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity user : users) {
            UserDTO dto = mapToDTO(user);
            userDTOs.add(dto);
        }

        return userDTOs;
    }

    private UserDTO mapToDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType() != null ? user.getUserType().name() : null,
                user.getCoachId(),
                user.getSport(),
                user.getClub(),
                user.getCreatedAt()
        );
    }


}
