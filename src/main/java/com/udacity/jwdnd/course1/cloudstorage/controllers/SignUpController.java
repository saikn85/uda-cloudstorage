package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.SignUpDto;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignUpController {
    private final UserService _userService;

    public SignUpController(UserService userService) {
        _userService = userService;
    }

    @GetMapping()
    public String get() {
        return "signup";
    }

    @PostMapping()
    public String post(@ModelAttribute SignUpDto signUpDto, Model model) {
        String signupError = null;
        if (!_userService.checkUserNameAvailability(signUpDto.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = _userService.createUser(new User(
                    0,
                    signUpDto.getUsername(),
                    "",
                    signUpDto.getPassword(),
                    signUpDto.getFirstName(),
                    signUpDto.getLastName()));
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError != null) {
            model.addAttribute("signupError", signupError);
            return "signup";
        }

        return "login";
    }
}
