package ru.otus.springwork17.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.springwork17.exception.BookNotFoundException;
import ru.otus.springwork17.model.Author;
import ru.otus.springwork17.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/author")
    public String getAuthor(Model model) {
        val authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "author";
    }

    @GetMapping(value = {"/authorEdit"})
    public String authorEditPage(@RequestParam(defaultValue = "0", name = "id") long id, Model model) {
        if (id == 0) {
            model.addAttribute("author", new Author());
        } else {
            model.addAttribute("isEdit", true);
            model.addAttribute("author", authorService.findById(id).orElseThrow(BookNotFoundException::new));
        }
        return "authorEdit";
    }

    @PostMapping("/authorEdit")
    public String saveAuthor(Author author) {
        authorService.save(author);
        return "redirect:/author";
    }
}
