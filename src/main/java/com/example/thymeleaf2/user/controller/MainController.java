package com.example.thymeleaf2.user.controller;

import com.example.thymeleaf2.user.dto.UserDto;
import com.example.thymeleaf2.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by User: Vu
 * Date: 06.07.2024
 * Time: 16:24
 */

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "success",required = false)String success, Model model) {
        if(success!=null){
            model.addAttribute("successMessage","Registration Successfull");
        }
        return "login";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user")UserDto dto){
        userService.save(dto);
        return "redirect:/login?success";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

}
