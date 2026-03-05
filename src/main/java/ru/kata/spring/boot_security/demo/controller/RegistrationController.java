package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.logging.Logger;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("userForm", new User());

        return "news";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("userForm", new User());

        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm")  User userForm, BindingResult bindingResult, Model model) {
        Logger log2 = Logger.getLogger(RegistrationController.class.getName());
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        /*if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }*/
        log2.info("controller Ya zashel");
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            log2.info("controller Ya vishel");
            return "registration";
        }

        return "redirect:/";
    }



    /*@GetMapping("/user_admin") // Или /users, или /test - как вам удобнее
    public String listUsers(Model model) {
        model.addAttribute("users", userService.allUsers()); // Проверьте имя атрибута "userss"
        return "users"; // Имя вашего JSP файла для списка
    }*/

    @GetMapping("/") // Или /users, или /test - как вам удобнее
    public String listUsers(@RequestParam("id") Long id, Model model) {
        model.addAttribute("users", userService.findUserById(id)); // Проверьте имя атрибута "userss"
        return "users"; // Имя вашего JSP файла для списка
    }

    // Страница добавления нового пользователя
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User()); // Имя атрибута "user"
        return "user-info"; // Имя вашего JSP файла с формой (должно быть user-info.jsp)
    }

    @PostMapping("/save")
    public String saveOrUpdateUser(@ModelAttribute("user") User user, Model model) {
        if (user.getId() == null) { // Создание нового
            if (!userService.saveUser(user)) {
                model.addAttribute("usernameError", "User already exists");
                return "user-info";
            }
        } else { // Редактирование - просто перенаправляем (без реального сохранения пока)
            // TODO: Реализовать userService.updateUser()
        }
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user-info";
    }


    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
