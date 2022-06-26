package ru.otus.springwork11.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BookController {

    @GetMapping("/book")
    public String getBook(Model model) {
        return "book";
    }

    @GetMapping("/book/edit/{id}")
    public String editBookPage(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("isEdit", true);
        return  "bookEdit";
    }

    @GetMapping("/book/edit")
    public String saveBookPage(Model model) {
        return "bookEdit";
    }
}