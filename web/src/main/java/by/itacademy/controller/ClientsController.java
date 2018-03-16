package by.itacademy.controller;

import by.itacademy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientsController {

    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
        public String findAllClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "clients";
    }
}