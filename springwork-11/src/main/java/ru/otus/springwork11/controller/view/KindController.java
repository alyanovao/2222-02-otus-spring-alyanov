package ru.otus.springwork11.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KindController {

    @GetMapping("/kindBook")
    public String getKindBook(Model model) {
        return "kindBook";
    }
}
