package by.itacademy.controller;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.entity.BaseEntity;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import by.itacademy.service.CreditApplicationService;
import by.itacademy.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CreditApplicationsViewController {

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
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_APPLICATIONS_PER_PAGE = 5;

    private final CreditService creditService;
    private final List<Long> creditList;

    @Autowired
    public CreditApplicationsViewController(CreditService creditService) {
        this.creditService = creditService;
        creditList = creditService.findAll().stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    @ModelAttribute("creditApplicationSearchFormDto")
    public CreditApplicationSearchFormDto creditApplicationSearchFormDto() {
        CreditApplicationSearchFormDto creditApplicationSearchFormDto = new CreditApplicationSearchFormDto();
        creditApplicationSearchFormDto.setDateFrom(LocalDate.now().minusYears(1));
        creditApplicationSearchFormDto.setDateTo(LocalDate.now());
        creditApplicationSearchFormDto.setAgeFrom(DEFAULT_AGE_FROM);
        creditApplicationSearchFormDto.setAgeTo(DEFAULT_AGE_TO);
        creditApplicationSearchFormDto.setChildrenFrom(DEFAULT_CHILDREN_FROM);
        creditApplicationSearchFormDto.setChildrenTo(DEFAULT_CHILDREN_TO);

        creditApplicationSearchFormDto.setGender(Arrays.asList(allGenders()));
        creditApplicationSearchFormDto.setCreditId(creditList);
        creditApplicationSearchFormDto.setClientRating(Arrays.asList(allClientRatings()));
        creditApplicationSearchFormDto.setMaritalStatus(Arrays.asList(allMaritalStatus()));
        creditApplicationSearchFormDto.setApplicationQuality(Arrays.asList(allApplicationQualities()));
        creditApplicationSearchFormDto.setScoringSystemResolution(Arrays.asList(allApplicationQualities()));

        creditApplicationSearchFormDto.setIncomeFrom(DEFAULT_INCOME_FROM);
        creditApplicationSearchFormDto.setIncomeTo(DEFAULT_INCOME_TO);
        creditApplicationSearchFormDto.setPledgeFrom(DEFAULT_PLEDGE_FROM);
        creditApplicationSearchFormDto.setPledgeTo(DEFAULT_PLEDGE_TO);
        creditApplicationSearchFormDto.setSumFrom(DEFAULT_SUM_FROM);
        creditApplicationSearchFormDto.setSumTo(DEFAULT_SUM_TO);
        creditApplicationSearchFormDto.setLoanPeriodFrom(DEFAULT_LOAN_PERIOD_FROM);
        creditApplicationSearchFormDto.setLoanPeriodTo(DEFAULT_LOAN_PERIOD_TO);
        creditApplicationSearchFormDto.setPage(DEFAULT_PAGE);
        creditApplicationSearchFormDto.setApplicationsPerPage(DEFAULT_APPLICATIONS_PER_PAGE);
        return creditApplicationSearchFormDto;
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

    @GetMapping("/credit_applications")
    public String getFilter() {
        return "credit_applications";
    }
}