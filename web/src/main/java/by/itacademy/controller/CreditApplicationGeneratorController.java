package by.itacademy.controller;

import by.itacademy.dto.CreditApplicationGeneratorDto;
import by.itacademy.dto.CreditApplicationSearchResultDto;
import by.itacademy.entity.Credit;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import by.itacademy.service.ClientService;
import by.itacademy.service.CreditApplicationGeneratorService;
import by.itacademy.service.CreditApplicationService;
import by.itacademy.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CreditApplicationGeneratorController {

    private static final int DEFAULT_AGE_FROM = 18;
    private static final int DEFAULT_AGE_TO = 60;
    private static final int DEFAULT_CHILDREN_FROM = 0;
    private static final int DEFAULT_CHILDREN_TO = 3;
    private static final int DEFAULT_INCOME_FROM = 100;
    private static final int DEFAULT_INCOME_TO = 5000;
    private static final int DEFAULT_PLEDGE_FROM = 100;
    private static final int DEFAULT_PLEDGE_TO = 10000;
    private static final int DEFAULT_SUM_FROM = 100;
    private static final int DEFAULT_SUM_TO = 30000;
    private static final int DEFAULT_LOAN_PERIOD_FROM = 12;
    private static final int DEFAULT_LOAN_PERIOD_TO = 36;
    private static final int DEFAULT_AMOUNT_OF_CLIENTS = 20;
    private static final int DEFAULT_AMOUNT_OF_APPLICATIONS = 50;
    private static final int DEFAULT_GOOD_APPLICATIONS_PERCENT = 50;

    private final ClientService clientService;
    private final CreditService creditService;
    private final CreditApplicationService creditApplicationService;
    private final CreditApplicationGeneratorService creditApplicationGeneratorService;
    private final List<Long> creditList;

    @Autowired
    public CreditApplicationGeneratorController(ClientService clientService, CreditService creditService, CreditApplicationService creditApplicationService, CreditApplicationGeneratorService creditApplicationGeneratorService) {
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditApplicationService = creditApplicationService;
        this.creditList = creditService.findAll().stream().map(Credit::getId).collect(Collectors.toList());
        this.creditApplicationGeneratorService = creditApplicationGeneratorService;
    }

    @ModelAttribute("creditApplicationGeneratorDto")
    public CreditApplicationGeneratorDto creditApplicationGeneratorDto() {
        CreditApplicationGeneratorDto creditApplicationGeneratorDto = new CreditApplicationGeneratorDto();
        creditApplicationGeneratorDto.setAmountOfClients(DEFAULT_AMOUNT_OF_CLIENTS);
        creditApplicationGeneratorDto.setAmountOfApplications(DEFAULT_AMOUNT_OF_APPLICATIONS);
        creditApplicationGeneratorDto.setAgeFrom(DEFAULT_AGE_FROM);
        creditApplicationGeneratorDto.setAgeTo(DEFAULT_AGE_TO);
        creditApplicationGeneratorDto.setChildrenFrom(DEFAULT_CHILDREN_FROM);
        creditApplicationGeneratorDto.setChildrenTo(DEFAULT_CHILDREN_TO);

        creditApplicationGeneratorDto.setGender(Arrays.asList(allGenders()));
        creditApplicationGeneratorDto.setClientRating(Arrays.asList(allClientRatings()));
        creditApplicationGeneratorDto.setMaritalStatus(Arrays.asList(allMaritalStatus()));
        creditApplicationGeneratorDto.setCreditId(creditList);

        creditApplicationGeneratorDto.setDateFrom(LocalDate.now());
        creditApplicationGeneratorDto.setDateTo(LocalDate.now());
        creditApplicationGeneratorDto.setIncomeFrom(DEFAULT_INCOME_FROM);
        creditApplicationGeneratorDto.setIncomeTo(DEFAULT_INCOME_TO);
        creditApplicationGeneratorDto.setPledgeFrom(DEFAULT_PLEDGE_FROM);
        creditApplicationGeneratorDto.setPledgeTo(DEFAULT_PLEDGE_TO);
        creditApplicationGeneratorDto.setSumFrom(DEFAULT_SUM_FROM);
        creditApplicationGeneratorDto.setSumTo(DEFAULT_SUM_TO);
        creditApplicationGeneratorDto.setLoanPeriodFrom(DEFAULT_LOAN_PERIOD_FROM);
        creditApplicationGeneratorDto.setLoanPeriodTo(DEFAULT_LOAN_PERIOD_TO);
        creditApplicationGeneratorDto.setGoodApplicationPercent(DEFAULT_GOOD_APPLICATIONS_PERCENT);
        return creditApplicationGeneratorDto;
    }

    @ModelAttribute("credits")
    public List<Long> allCredits() {
        return creditList;
    }

    @ModelAttribute("clientRating")
    public ClientRating[] allClientRatings() {
        return ClientRating.values();
    }

    @ModelAttribute("gender")
    public Gender[] allGenders() {
        return Gender.values();
    }

    @ModelAttribute("maritalStatus")
    public MaritalStatus[] allMaritalStatus() {
        return MaritalStatus.values();
    }

    @ModelAttribute("applicationQuality")
    public ApplicationQuality[] allApplicationQualities() {
        return ApplicationQuality.values();
    }

    @GetMapping("/god/generator")
    public String getCreditApplicationsFilter() {
        return "/god/generator";
    }

    @PostMapping("/god/generator")
    @ResponseBody
    public List<CreditApplicationSearchResultDto> getSearchFormDto(@RequestBody CreditApplicationGeneratorDto dto) {
        List<CreditApplication> creditApplications =
                            creditApplicationGeneratorService.createCreditApplications(dto);
        return creditApplicationService.save(creditApplications).stream()
                .map(CreditApplicationSearchResultDto::new)
                .collect(Collectors.toList());
    }

    @RequestMapping("/god/generator/clear")
    public String clear() {
        clientService.deleteAllByIfRealIsFalse();
        return "/god/generator";
    }
}
