package com.portfolio_generator.app.controllers;

import com.portfolio_generator.app.file_upload.FileUploadUtil;
import com.portfolio_generator.app.models.User;
import com.portfolio_generator.app.repositories.UserRepository;
import com.portfolio_generator.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class WebController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/")
    @PreAuthorize("permitAll()")
    public String getMainView(Model model) {
        model.addAttribute("user", userService.findAll());
        model.addAttribute("welcome", "Welcome to Portfolio Generator");

        return "index";
    }

    @GetMapping(value = "/user/{username}")
    public String findUserById(@PathVariable("username") String userName, Model model) {
        model.addAttribute("user", userService.findByUserName(userName));
        return "/fragments/main";
    }

    @GetMapping(value = "/register")
    @PreAuthorize("isAnonymous()")
    public String registrationForm(Model model, User user) {


        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");

        return "registration_form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String userProfile = StringUtils.cleanPath("/images/UserProfile.png");
        if (user.getPhotos() == null) {
            user.setPhotos(userProfile);
        }
        user.setPhotos(fileName);

        User savedUser = userService.save(user);

        String uploadDir = "user-photos/" + savedUser.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    @PreAuthorize("permitAll()")
    public String login(Model model) {
        model.addAttribute("title", "login");
        return "login";
    }


}
