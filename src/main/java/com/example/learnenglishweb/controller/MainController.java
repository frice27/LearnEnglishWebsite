package com.example.learnenglishweb.controller;

import com.example.learnenglishweb.DTO.UserDTO;
import com.example.learnenglishweb.Entity.User;
import com.example.learnenglishweb.model.FullName;
import com.example.learnenglishweb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final UserServiceImpl userService;

    @Autowired
    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping
    private String index() {
        return "index";
    }

    @GetMapping("/openGrammarLess")
    public String openGrammarLess() {
        return "select-grammar";
    }

    @GetMapping("/writingA1")
    public String writingPage(@RequestParam String level) {
        return "writing";
    }

    @GetMapping("grammarA1")
    public String grammarPage(@RequestParam String level) {
        return "grammar";
    }


}
