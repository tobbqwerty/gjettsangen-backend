package com.ezdevz.gjett_sangen.controller;

import com.ezdevz.gjett_sangen.dto.RegisterDto;
import com.ezdevz.gjett_sangen.dto.UserDto;
import com.ezdevz.gjett_sangen.mapper.UserMapper;
import com.ezdevz.gjett_sangen.model.User;
import com.ezdevz.gjett_sangen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        LOGGER.info("GET request for all users: " + users.toString());
        List<UserDto> userDtos = users.stream()
                .map(UserMapper::toDTO)
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserbyUsername(@PathVariable String username) {
        LOGGER.info("GET request for user with username: " + username);
        User user = userService.getUserByUsername(username);
        UserDto userDto = UserMapper.toDTO(user);
        LOGGER.info("Mapped user to userDTO: {}", userDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserbyEmail(@PathVariable String email) {
        LOGGER.info("GET request for user with email: " + email);
        User user = userService.getUserByEmail(email);
        UserDto userDto = UserMapper.toDTO(user);
        LOGGER.info("Mapped user to userDTO: {}", userDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserbyId(@PathVariable Long id) {
        LOGGER.info("GET request for user with id: " + id);
        User user = userService.getUserById(id);
        UserDto userDto = UserMapper.toDTO(user);
        LOGGER.info("Mapped user to userDTO: {}", userDto);
        return ResponseEntity.ok(userDto);
    }
}
