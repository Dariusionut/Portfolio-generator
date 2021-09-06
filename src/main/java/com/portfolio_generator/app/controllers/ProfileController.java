package com.portfolio_generator.app.controllers;

import com.portfolio_generator.app.services.DetailsService;
import com.portfolio_generator.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    DetailsService detailsService;

    @GetMapping(value = "/users/profile/{id}")
    public String getProfile(@PathVariable("id") Long id, Model model, String userName) {
        model.addAttribute("user", userService.findById(id));
        return "/user/user_profile";
    }

}
