package ru.otus.springwork17.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.springwork17.service.KindBookService;

@Controller
@RequiredArgsConstructor
public class KindController {

    private final KindBookService kindBookService;

    @GetMapping("/kindBook")
    public String getKindBook(Model model) {
        val kinds = kindBookService.findAll();
        model.addAttribute("kinds", kinds);
        return "kindBook";
    }
}
