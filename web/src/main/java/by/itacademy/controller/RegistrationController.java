package by.itacademy.controller;

import by.itacademy.dto.CurrencyExchangeRateDto;
import by.itacademy.entity.Child;
import by.itacademy.entity.Client;
import by.itacademy.entity.Role;
import by.itacademy.service.ClientService;
import by.itacademy.service.CurrencyExchangeRateService;
import by.itacademy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    private final CurrencyExchangeRateService currencyExchangeRateService;
    private final RoleService roleService;
    private final ClientService clientService;

    @Autowired
    public RegistrationController(CurrencyExchangeRateService currencyExchangeRateService, RoleService roleService, ClientService clientService) {
        this.currencyExchangeRateService = currencyExchangeRateService;
        this.roleService = roleService;
        this.clientService = clientService;
    }

    @ModelAttribute("exchangeRates")
    public List<CurrencyExchangeRateDto> exchangeRates() {
        return currencyExchangeRateService.findAll().stream().map(CurrencyExchangeRateDto::new)
                .collect(Collectors.toList());
    }

    @ModelAttribute("roles")
    public List<Role> allRoles() {
        return roleService.findAll();
    }

    @ModelAttribute("client")
    public Client client() {
        Client client = new Client();
        client.setChildren(Collections.emptyList());
        return client;
    }

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public String postRegistrationForm(@RequestBody Client client) {
        client.setRoles(Arrays.asList(new Role(1L, "USER")));
        client.setIfReal(true);
        for (Child child : client.getChildren()) {
            child.setIfReal(true);
        }
        clientService.save(client);
        return "SUCCESS";
    }
}