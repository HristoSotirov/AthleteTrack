package com.athletetrack.service;

import com.athletetrack.DTO.UserDTO;
import com.athletetrack.entity.UserEntity;
import com.athletetrack.jwtToken.JwtTokenUtil;
import com.athletetrack.repository.UserRepository;
import com.athletetrack.security.PasswordUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO mapToDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType() != null ? user.getUserType().name() : null,
                user.getCoachId(),
                user.getCoachName(),
                user.getSport(),
                user.getClub(),
                user.getCreatedAt()
        );
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

    public UserDTO getUserById(Long userId) throws SQLException {
        UserEntity user = userRepository.getUserById(userId);
        if (user == null) {
            return null;
        }
        return mapToDTO(user);
    }

    public void registerCoach(UserDTO user) throws SQLException {
        String encryptedPassword = PasswordUtils.encryptPassword(user.getPassword());
        UserEntity coach = new UserEntity(
                user.getName(),
                user.getUsername(),
                encryptedPassword,
                user.getEmail(),
                user.getPhone(),
                UserEntity.UserType.COACH,
                user.getSport(),
                user.getClub()
        );
        userRepository.saveCoach(coach);
    }

    public void registerAthlete(Long coachId, UserDTO user) throws SQLException {
        String encryptedPassword = PasswordUtils.encryptPassword(user.getPassword());
        UserEntity athlete = new UserEntity(
                user.getName(),
                user.getUsername(),
                encryptedPassword,
                user.getEmail(),
                user.getPhone(),
                UserEntity.UserType.ATHLETE
        );
        userRepository.saveAthlete(coachId, athlete);
    }

    public String login(UserDTO user) throws SQLException {
        String encryptedPassword = PasswordUtils.encryptPassword(user.getPassword());
        UserEntity userEntity = new UserEntity(
                user.getUsername(),
                encryptedPassword
        );
        UserEntity userJWT = userRepository.login(userEntity);
        return JwtTokenUtil.generateToken(userJWT.getId(),userJWT.getUsername());
    }

}
