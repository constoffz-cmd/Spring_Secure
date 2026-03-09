package ru.kata.spring.boot_security.demo.service;




import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServiceInter {
    boolean saveUser(User user);

    List<User> listUsers();

    User getUserById(Long Id);

    void updateUser(User user);

    boolean deleteUser(Long id);
}