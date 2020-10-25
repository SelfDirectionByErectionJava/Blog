package com.dolinini.blog.controllers;

import com.dolinini.blog.domain.Role;
import com.dolinini.blog.domain.User;
import com.dolinini.blog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model){
        return "/registration";
    }
    @PostMapping("/registration")
    public String adduser(User user, Model model){
        User userFromDb=userRepo.findByUsername(user.getUsername());
        if(userFromDb!=null) {
            model.addAttribute("message", "user already exists!");
            return "registration";
        }
        user.setActivity(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
