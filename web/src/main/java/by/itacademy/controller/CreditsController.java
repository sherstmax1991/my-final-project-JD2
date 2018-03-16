package by.itacademy.controller;

import by.itacademy.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreditsController {

    private final CreditService creditService;

    @Autowired
    public CreditsController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credits")
    public String findAllClients(Model model) {
        model.addAttribute("credits", creditService.findAll());
        return "credits";
    }
}