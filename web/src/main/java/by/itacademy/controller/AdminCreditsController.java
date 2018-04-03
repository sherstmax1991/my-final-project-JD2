package by.itacademy.controller;

import by.itacademy.entity.Credit;
import by.itacademy.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminCreditsController {

    private final CreditService creditService;

    @Autowired
    public AdminCreditsController(CreditService creditService) {
        this.creditService = creditService;
    }

    @ModelAttribute("credits")
    public List<Credit> exchangeRates() {
        return creditService.findAll();
    }

    @GetMapping("/admin/credits")
    public String getExchangeRatesEditor() {
        return "admin/exchangeRates";
    }

    @PostMapping("/admin/credits")
    public String saveCredit(Credit credit) {
//        Credit credit = new Credit(creditDto);
        creditService.save(credit);
        return "redirect: /admin/exchangeRates";
    }
}