package com.ezdevz.gjett_sangen.service;


import com.ezdevz.gjett_sangen.dto.RegisterDto;
import com.ezdevz.gjett_sangen.model.Role;
import com.ezdevz.gjett_sangen.model.User;
import com.ezdevz.gjett_sangen.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(@NonNull Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }

    @Override
    public User getUserByEmail(@NonNull String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public User getUserByUsername(@NonNull String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User registerUser(RegisterDto register) throws Exception {
        if (usernameExists(register.getUsername())) {
            throw new Exception("Username already in use!");
        }
        if (emailExists(register.getEmail())) {
            throw new Exception("Email already in use!");
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole(Role.USER);

        return saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }


}
