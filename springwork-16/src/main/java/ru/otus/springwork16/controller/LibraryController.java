package ru.otus.springwork16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("/")
    public String getMain() {
        return "main";
    }

}