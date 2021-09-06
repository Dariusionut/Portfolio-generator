package com.portfolio_generator.app.controllers;

import com.portfolio_generator.app.exceptions.UserNotFoundException;
import com.portfolio_generator.app.services.DetailsService;
import com.portfolio_generator.app.services.RoleService;
import com.portfolio_generator.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DetailsService detailsService;


    @GetMapping
    @PreAuthorize("permitAll()")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("title", "Users");
        return "user/user_list";
    }

    @GetMapping(value = "/portfolio/{id}")
    @PreAuthorize("permitAll()")
    public String getUserPortfolio(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.findById(id));

        return "/user/user_portfolio";
    }

    @GetMapping(value = "/search" )
    @PreAuthorize("permitAll()")
    public String search(@RequestParam("userName") String name, Model model) {
        model.addAttribute("users", userService.searchBy(name));
        return "/user/user_list";
    }



    @GetMapping("/edit/{id}")

    public String editUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.findById(id));
        model.addAttribute("title", "Edit User");

        return "registration_form";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return "redirect:/users";
    }


}
