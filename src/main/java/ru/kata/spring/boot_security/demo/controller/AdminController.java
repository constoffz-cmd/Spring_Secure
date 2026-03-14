package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceInter;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceInter;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceInter userService;
    private final RoleServiceInter roleService;

    @Autowired
    public AdminController(UserServiceInter userService, RoleServiceInter roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = {"", "/users"})
    public String listUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("role", roleService.getAllRoles());
        return "users";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles()); // Отправляем роли на фронт
        return "user-info";
    }


    @PostMapping("/save")
    public String saveOrUpdateUser(@ModelAttribute("user") User user,
                                   @RequestParam(value = "selectedRoles", required = false) List<Long> roleIds,
                                   Model model) {

        if (roleIds != null) {
            user.setRoles(roleService.findRolesByIds(roleIds));
        }

        if (user.getId() == null) {
            if (!userService.saveUser(user)) {
                model.addAttribute("usernameError", "User already exists");
                model.addAttribute("allRoles", roleService.getAllRoles());
                return "user-info";
            }
        } else {
            userService.updateUser(user);
        }
        return "redirect:/admin/users";
    }


    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "user-info";
    }


    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "redirect:/admin/add";
    }
}