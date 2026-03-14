package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceInter;

import java.util.logging.Logger;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
