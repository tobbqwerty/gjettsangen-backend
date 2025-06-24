package com.ezdevz.gjett_sangen.service;


import com.ezdevz.gjett_sangen.dto.RegisterDto;
import com.ezdevz.gjett_sangen.dto.UpdateUserDto;
import com.ezdevz.gjett_sangen.dto.UserDto;
import com.ezdevz.gjett_sangen.model.User;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User registerUser(RegisterDto register) throws Exception;

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public User getUserById(Long id);

    public boolean usernameExists(String username);

    public boolean emailExists(String email);

    public User saveUser(User user);

    public User updateUser(User user, UpdateUserDto updateUserDto);

    public void deleteUserById(Long id);

    public void deleteUser(User user);

}
