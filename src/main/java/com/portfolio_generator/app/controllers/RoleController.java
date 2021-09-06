package com.portfolio_generator.app.controllers;

import com.portfolio_generator.app.models.Role;
import com.portfolio_generator.app.services.RoleService;
import com.portfolio_generator.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@Controller
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "roles");

        return "role/role_list";
    }

    @GetMapping(value = "/add")
    public String roleForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "Add Role");

        return "role/role_form";
    }

    @GetMapping(value = "/edit/{id}")
    public String editRole(@PathVariable("id") Long id, Model model) throws RoleNotFoundException {
        model.addAttribute("role", roleService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "Edit Role");

        return "role/role_form";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("role") Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id) throws RoleNotFoundException {
        roleService.deleteById(id);
        return "redirect:/roles";
    }


}
