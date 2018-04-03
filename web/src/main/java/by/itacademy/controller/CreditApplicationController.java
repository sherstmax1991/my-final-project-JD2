package by.itacademy.controller;

import by.itacademy.dto.CreditApplicationDto;
import by.itacademy.dto.CreditApplicationNeuronNetworkDto;
import by.itacademy.dto.CurrencyExchangeRateDto;
import by.itacademy.entity.Client;
import by.itacademy.entity.Credit;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.service.ClientService;
import by.itacademy.service.CreditApplicationService;
import by.itacademy.service.CreditService;
import by.itacademy.service.CurrencyExchangeRateService;
import by.itacademy.service.neuronNetworkService.CreditApplicationAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CreditApplicationController {

    private static final int DEFAULT_INCOME = 500;
    private static final int DEFAULT_LOAN_PERIOD = 24;
    private static final int DEFAULT_PLEDGE = 500;
    private static final int DEFAULT_SUM = 1000;
    private static final long CREDIT_ID = 1L;
    private final CurrencyExchangeRateService currencyExchangeRateService;
    private final ClientService clientService;
    private final CreditService creditService;
    private final CreditApplicationService creditApplicationService;
    private final CreditApplicationAnalyzer creditApplicationAnalyzer;

    @Autowired
    public CreditApplicationController(CurrencyExchangeRateService currencyExchangeRateService,
                                       ClientService clientService, CreditService creditService,
                                       CreditApplicationService creditApplicationService,
                                       CreditApplicationAnalyzer creditApplicationAnalyzer) {
        this.currencyExchangeRateService = currencyExchangeRateService;
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditApplicationService = creditApplicationService;
        this.creditApplicationAnalyzer = creditApplicationAnalyzer;
    }

    @ModelAttribute("creditApplicationDto")
    public CreditApplicationDto defaultCreditApplicationDto() {
        CreditApplicationDto creditApplicationDto = new CreditApplicationDto();
        creditApplicationDto.setCreditId(CREDIT_ID);
        creditApplicationDto.setIncome(DEFAULT_INCOME);
        creditApplicationDto.setLoanPeriod(DEFAULT_LOAN_PERIOD);
        creditApplicationDto.setPledge(DEFAULT_PLEDGE);
        creditApplicationDto.setSum(DEFAULT_SUM);
        return creditApplicationDto;
    }

    @ModelAttribute("exchangeRates")
    public List<CurrencyExchangeRateDto> exchangeRates() {
        return currencyExchangeRateService.findAll().stream().map(CurrencyExchangeRateDto::new)
                .collect(Collectors.toList());
    }

    @ModelAttribute("credits")
    public List<Credit> credits() {
        return creditService.findAll();
    }

    @GetMapping("/creditApplication")
    public String getDefaultApplicationForm() {
        return "creditApplication";
    }

    @PostMapping("/creditApplication")
    public String sendCreditApplication(CreditApplicationDto creditApplicationDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByUsername(user.getUsername());
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setClient(client);
        creditApplication.setApplicationDate(LocalDate.now());
        creditApplication.setCredit(creditService.findOne(creditApplicationDto.getCreditId()));
        creditApplication.setIncome(creditApplicationDto.getIncome());
        creditApplication.setPledge(creditApplicationDto.getPledge());
        creditApplication.setSum(creditApplicationDto.getSum());
        creditApplication.setLoanPeriod(creditApplicationDto.getLoanPeriod());
        CreditApplicationNeuronNetworkDto creditApplicationNeuronNetworkDto =
                                                            new CreditApplicationNeuronNetworkDto(creditApplication);
        ApplicationQuality applicationQuality = creditApplicationAnalyzer
                                                        .analyzeCreditApplication(creditApplicationNeuronNetworkDto);
        if (applicationQuality == ApplicationQuality.GOOD) {
            creditApplication.setApplicationQuality(applicationQuality);
            creditApplication.setScoringSystemResolution(applicationQuality);
            creditApplicationService.save(creditApplication);
        }
        return "home";
    }
}
