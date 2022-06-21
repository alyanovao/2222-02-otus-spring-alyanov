package ru.otus.springwork11.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    @GetMapping("/authors")
    public String getAuthor(Model model) {
        return "author";
    }

   @GetMapping("/author/edit/{id}")
    public String saveAuthor(@PathVariable(value = "id") String id, Model model) {
        model.addAttribute("isEdit", Boolean.TRUE);
        return "authorEdit";
    }

    @GetMapping("/author/edit")
    public String addAuthor(Model model) {
        model.addAttribute("isEdit", Boolean.FALSE);
        return "authorEdit";
    }
}