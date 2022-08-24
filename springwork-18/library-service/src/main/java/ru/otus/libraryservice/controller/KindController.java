package ru.otus.libraryservice.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.libraryservice.availible.KindBookSupplierService;
import ru.otus.libraryservice.model.KindBook;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Controller
public class KindController {

    private final Supplier<List<KindBook>> decorateKindBookService;

    public KindController(KindBookSupplierService kindBookSupplierService) {
        this.decorateKindBookService = kindBookSupplierService.getKindBookSupplier();
    }

    @GetMapping("/kindBook")
    public String getKindBook(Model model) {
        List<KindBook> kinds = null;
        try {
            kinds = decorateKindBookService.get();
        }
        catch (Exception e) {
            log.error("Could not get kindBook");
        }
        model.addAttribute("kinds", kinds);
        return "kindBook";
    }
}
