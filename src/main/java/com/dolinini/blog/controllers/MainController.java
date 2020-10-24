package com.dolinini.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Model model) {

        return "greeting";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "MainPage");
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","Page about as");
        return "about";
    }


}
