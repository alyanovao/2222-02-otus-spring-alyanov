package ru.otus.libraryservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.libraryservice.availible.AuthorSupplierService;
import ru.otus.libraryservice.exception.BookNotFoundException;
import ru.otus.libraryservice.model.Author;
import ru.otus.libraryservice.service.AuthorService;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;

    private final Supplier<List<Author>> decorateAuthorService;

    public AuthorController(AuthorSupplierService service, AuthorService authorService) {
        this.decorateAuthorService = service.getAuthorsSupplier();
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public String getAuthor(Model model) {

        List<Author> authors = null;

        try {
            authors = decorateAuthorService.get();
        }
        catch(Exception e) {
            log.error("Could not get author");
        }

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
