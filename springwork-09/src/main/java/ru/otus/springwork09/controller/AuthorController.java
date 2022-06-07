package ru.otus.springwork09.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.springwork09.exception.BookNotFoundException;
import ru.otus.springwork09.model.Author;
import ru.otus.springwork09.service.AuthorService;

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
    public String authorEditPage(@RequestParam(required = false, name = "id") long id, Model model) {
        val author = authorService.findById(id).orElseThrow(BookNotFoundException::new);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("/authorEdit")
    public String saveAuthor(Author author) {
        authorService.save(author);
        return "redirect:/author";
    }

    @GetMapping("/authorAdd")
    public String authorAddPage(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "authorAdd";
    }

    @PostMapping("/authorAdd")
    public  String addAuthor(Author author) {
        authorService.save(author);
        return "redirect:/author";
    }
}
