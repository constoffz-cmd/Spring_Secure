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
        //model.addAttribute("userForm", new User());

        return "user-info";
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




}
