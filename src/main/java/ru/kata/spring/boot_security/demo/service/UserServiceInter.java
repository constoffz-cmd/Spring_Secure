package ru.kata.spring.boot_security.demo.service;




import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServiceInter {
    boolean saveUser(User user);
    User findUserById(Long Id);
    void updateUser(User user);
    boolean deleteUser(Long id);
    public List<User> allUsers();
    public User findByUsername(String username);
}