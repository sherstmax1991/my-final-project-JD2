package by.itacademy.controller;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.CreditApplicationSearchResultPageDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.CreditApplication;
import by.itacademy.service.CreditApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;

    @Autowired
    public CreditApplicationController(CreditApplicationService creditApplicationService) {
        this.creditApplicationService = creditApplicationService;
    }

    @PostMapping("/credit_applications")
    public CreditApplicationSearchResultPageDto getCreditApplicationSearchFormDto(@RequestBody CreditApplicationSearchFormDto formDto) {

        System.out.println("formDto " + formDto);

        PageableSearchResult<CreditApplication> searchResult = creditApplicationService.findByParameters(formDto);
        return new CreditApplicationSearchResultPageDto(searchResult);
    }
}
