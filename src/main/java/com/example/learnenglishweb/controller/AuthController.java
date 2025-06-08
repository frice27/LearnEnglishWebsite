package com.example.learnenglishweb.controller;

import com.example.learnenglishweb.DTO.UserDTO;
import com.example.learnenglishweb.Entity.User;
import com.example.learnenglishweb.repository.UserRepository;
import com.example.learnenglishweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String auth(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute UserDTO user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User userSave = new User(
                encodedPassword,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
        userService.save(userSave);

        return "redirect:/auth/registration";
    }

    @GetMapping("/registration")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "ListUser";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDTO());
        return "loginForm";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") UserDTO userDto, Model model) {
        User user = userService.findByEmail(userDto.getEmail());

        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            // Пароль вірний
            return "redirect:/main";
        }
        else {
            // Помилка логіну
            model.addAttribute("error", "Невірний email або пароль");
            return "loginForm";
        }
    }
}

