package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "users";
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "index";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "users";
    }

    // Страница добавления нового пользователя
    @GetMapping("/admin/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User()); // Имя атрибута "user"
        return "user-info"; // Имя вашего JSP файла с формой (должно быть user-info.jsp)
    }

    @PostMapping("/admin/save")
    public String saveOrUpdateUser(@ModelAttribute("user") User user, Model model) {
        if (user.getId() == null) { // Создание нового
            if (!userService.saveUser(user)) {
                model.addAttribute("usernameError", "User already exists");
                return "user-info";
            }
        } else { // Редактирование - просто перенаправляем (без реального сохранения пока)
            // TODO: Реализовать userService.updateUser()
        }
        //return "users";
        return "redirect:/";
    }


    @GetMapping("/admin/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user-info";
    }


    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        //return "users";
        return "redirect:/";
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        // Получаем всех пользователей, а не одного по ID
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

}
