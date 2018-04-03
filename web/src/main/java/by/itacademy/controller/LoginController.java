package by.itacademy.controller;

import by.itacademy.dto.CurrencyExchangeRateDto;
import by.itacademy.service.CurrencyExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    private final CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    public LoginController(CurrencyExchangeRateService currencyExchangeRateService) {
        this.currencyExchangeRateService = currencyExchangeRateService;
    }

    @ModelAttribute("exchangeRates")
    public List<CurrencyExchangeRateDto> exchangeRates() {
        return currencyExchangeRateService.findAll().stream().map(CurrencyExchangeRateDto::new).collect(Collectors.toList());
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}