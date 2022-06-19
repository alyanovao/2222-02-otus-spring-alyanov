package ru.otus.springwork10.controller.view;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.springwork10.exception.BookNotFoundException;
import ru.otus.springwork10.model.Author;
import ru.otus.springwork10.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    public String getAuthor(Model model) {
        return "author";
    }

   @GetMapping("/author/edit/{id}")
    public String saveAuthor(@PathVariable(value = "id", required = false) Long id, Model model) {
        val author = authorService.findById(id).orElseThrow(BookNotFoundException::new);
        model.addAttribute("isEdit", Boolean.TRUE);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @GetMapping("/author/edit")
    public String addAuthor(Model model) {
        val author = new Author();
        model.addAttribute("isEdit", Boolean.FALSE);
        model.addAttribute("author", author);
        return "authorEdit";
    }
}