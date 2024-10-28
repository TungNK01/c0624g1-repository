package com.codegym.c0624g1repository.controller;

import com.codegym.c0624g1repository.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("counter")
@RequestMapping("/counter")
public class CounterController {

    @Autowired
    private HttpSession httpSession;

    @ModelAttribute("counter")
    public Counter setUpCounter() {
        return new Counter();
    }

    @GetMapping("")
    public String get(@ModelAttribute("counter") Counter counter) {
        counter.increment();
        return "/counter/index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        httpSession.setAttribute("userName", "Tran Quang Huy");
        return "/counter/login";
    }
}
