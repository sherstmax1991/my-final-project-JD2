package by.itacademy.controller;

import by.itacademy.dto.ClientCreditInfoDto;
import by.itacademy.dto.ClientDto;
import by.itacademy.dto.CurrencyExchangeRateDto;
import by.itacademy.entity.Child;
import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;
import by.itacademy.service.ClientService;
import by.itacademy.service.CreditApplicationService;
import by.itacademy.service.CurrencyExchangeRateService;
import by.itacademy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProfileController {

    private final ClientService clientService;
    private final CreditApplicationService creditApplicationService;
    private final CurrencyExchangeRateService currencyExchangeRateService;
    private final RoleService roleService;

    @Autowired
    public ProfileController(ClientService clientService, CreditApplicationService creditApplicationService,
                             CurrencyExchangeRateService currencyExchangeRateService, RoleService roleService) {
        this.clientService = clientService;
        this.creditApplicationService = creditApplicationService;
        this.currencyExchangeRateService = currencyExchangeRateService;
        this.roleService = roleService;
    }

    @ModelAttribute("exchangeRates")
    public List<CurrencyExchangeRateDto> exchangeRates() {
        return currencyExchangeRateService.findAll().stream().map(CurrencyExchangeRateDto::new)
                                                                .collect(Collectors.toList());
    }

    @ModelAttribute("client")
    public Client client() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientService.findByUsername(user.getUsername());
    }

    @ModelAttribute("clientDto")
    public ClientDto clientDto() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ClientDto(clientService.findByUsername(client().getUsername()));
    }

    @GetMapping("/profile")
    public String getClientInfo() {
        return "profile";
    }

    @PostMapping("/profile/update")
    @ResponseBody
    public String updateClientInfo(@RequestBody ClientDto clientDto, Model model) {
        Client client = (Client) model.asMap().get("client");
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setBirthday(clientDto.getBirthday());
        client.setGender(clientDto.getGender());
        client.setRating(clientDto.getRating());
        client.setMaritalStatus(clientDto.getMaritalStatus());
        for (Child child: clientDto.getChildren()) {
            child.setIfReal(true);
        }
        client.setChildren(clientDto.getChildren());
        clientService.save(client);
        return "SUCCESS";
    }

    @RequestMapping("/profile/applications")
    @ResponseBody
    public List<ClientCreditInfoDto> getClientApplications() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CreditApplication> allByClient = creditApplicationService.findAllByClientUsername(user.getUsername());
        return allByClient.stream().map(ClientCreditInfoDto::new).collect(Collectors.toList());
    }
}
