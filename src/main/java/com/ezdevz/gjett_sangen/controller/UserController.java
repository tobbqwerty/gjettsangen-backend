package com.ezdevz.gjett_sangen.controller;

import com.ezdevz.gjett_sangen.auth.JwtService;
import com.ezdevz.gjett_sangen.dto.ForgotPasswordDto;
import com.ezdevz.gjett_sangen.dto.LoginDto;
import com.ezdevz.gjett_sangen.dto.RegisterDto;
import com.ezdevz.gjett_sangen.dto.UserDto;
import com.ezdevz.gjett_sangen.mapper.UserMapper;
import com.ezdevz.gjett_sangen.model.User;
import com.ezdevz.gjett_sangen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        try {
            LOGGER.info("Attempting authentication for user: {}", loginDto.getUsername());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            User user = userService.getUserByUsername(loginDto.getUsername());
            String token = jwtService.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());

            LOGGER.info("User {} successfully authenticated", loginDto.getUsername());
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            LOGGER.error("Authentication failed for user: {}", loginDto.getUsername());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createUser(@RequestBody RegisterDto register) throws Exception {
        LOGGER.info("POST request for user: {}", register);
        User savedUser = userService.registerUser(register);

        LOGGER.info("User created with ID: {}", savedUser.getId());

        UserDto savedUserDto = UserMapper.toDTO(savedUser);
        LOGGER.info("Mapped User to UserDTO: {}", savedUserDto);

        String token = jwtService.generateToken(savedUser);
        LOGGER.info("JWT token generated successfully");

        Map<String, Object> response = new HashMap<>();
        response.put("user", savedUserDto);
       response.put("token", token);
        response.put("role", savedUser.getRole());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ForgotPasswordDto forgotPassword) {


        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/users/reset")
    public ResponseEntity<?> newPassword(@RequestBody String newPassword) {

        return ResponseEntity.ok().build();
    }




}
