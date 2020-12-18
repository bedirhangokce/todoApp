package com.todo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @RequestMapping("/")
    public ModelAndView defaultHome() {
        return new ModelAndView("home");
    }
}
