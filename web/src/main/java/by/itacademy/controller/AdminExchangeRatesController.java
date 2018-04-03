package by.itacademy.controller;

import by.itacademy.dto.CurrencyExchangeRateEditResultDto;
import by.itacademy.entity.CurrencyExchangeRate;
import by.itacademy.service.CurrencyExchangeRateService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminExchangeRatesController {

    private final CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    public AdminExchangeRatesController(CurrencyExchangeRateService currencyExchangeRateService) {
        this.currencyExchangeRateService = currencyExchangeRateService;
    }

    @ModelAttribute("exchangeRates")
    public List<CurrencyExchangeRate> exchangeRates() {
        return currencyExchangeRateService.findAll();
    }

    @GetMapping("/admin/exchangeRates")
    public String getExchangeRatesEditor() {
        return "admin/exchangeRates";
    }

    @PostMapping("/admin/exchangeRates")
    public String saveExchangeRate(CurrencyExchangeRateEditResultDto exchangeRate) {
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate(exchangeRate);
        try {
        currencyExchangeRateService.save(currencyExchangeRate);

        } catch (StaleObjectStateException e) {
            return "redirect: /admin/creditApplications";
        }
        return "redirect: /admin/exchangeRates";
    }
}
