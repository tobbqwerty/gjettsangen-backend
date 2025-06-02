package com.ezdevz.gjett_sangen.service;


import com.ezdevz.gjett_sangen.model.User;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User createUser();

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public User getUserById(Long id);

    public boolean usernameExists(String username);

    public boolean emailExists(String email);

    public User saveUser(User user);

    public void deleteUserById(Long id);

    public void deleteUser(User user);

}
