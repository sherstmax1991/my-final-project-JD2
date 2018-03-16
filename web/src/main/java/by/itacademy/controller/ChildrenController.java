package by.itacademy.controller;

import by.itacademy.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChildrenController {

    private ChildService childService;

    @Autowired
    public ChildrenController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/children")
        public String findAllClients(Model model) {
        model.addAttribute("children", childService.findAll());
        return "children";
    }
}