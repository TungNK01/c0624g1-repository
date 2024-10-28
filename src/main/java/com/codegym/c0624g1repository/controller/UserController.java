package com.codegym.c0624g1repository.controller;

import com.codegym.c0624g1repository.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, HttpServletResponse response) {
        // For simplicity, we'll just set a cookie with the username
        Cookie cookie = new Cookie("usernameCookie", user.getUsername());
        response.addCookie(cookie);
        return "redirect:/user/welcome";
    }

    @GetMapping("/welcome")
    public String welcomeUser(@CookieValue(value = "usernameCookie",
            defaultValue = "Guest") String username, Model model)
    {
        model.addAttribute("username", username);
        return "/user/welcome";
    }
}
