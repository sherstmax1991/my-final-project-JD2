package by.itacademy.service;

import by.itacademy.dto.CreditApplicationGeneratorDto;
import by.itacademy.entity.Child;
import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.Role;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class CreditApplicationGeneratorServiceImpl implements CreditApplicationGeneratorService {

    private static final long END_OF_CHILDHOOD = 18;
    private CreditService creditService;
    private ClientService clientService;
    private BasicGenerator generator;
    private NamesGeneratorService namesGeneratorService;
    private LastNameService lastNameService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CreditApplicationGeneratorServiceImpl(CreditService creditService, ClientService clientService, BasicGenerator generator, NamesGeneratorService namesGeneratorService, LastNameService lastNameService, PasswordEncoder passwordEncoder) {
        this.creditService = creditService;
        this.clientService = clientService;
        this.generator = generator;
        this.namesGeneratorService = namesGeneratorService;
        this.lastNameService = lastNameService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<CreditApplication> createCreditApplications(CreditApplicationGeneratorDto dto) {
        int amountOfApplications = dto.getAmountOfApplications();
        List<CreditApplication> listOfCreditApplications = new ArrayList<>(amountOfApplications);
        List<Client> clients = createClients(dto);
        while (amountOfApplications > 0) {
            CreditApplication creditApplication = new CreditApplication();
            creditApplication.setClient(generator.getRandomElement(clients));
            creditApplication.setCredit(creditService.findOne(generator.getRandomElement(dto.getCreditId())));
            creditApplication.setApplicationDate(generator.getRandomDateBetween(dto.getDateFrom(), dto.getDateTo()));
            creditApplication.setIncome(generator.getRandomNumber(dto.getIncomeFrom(), dto.getIncomeTo()));
            creditApplication.setPledge(generator.getRandomNumber(dto.getPledgeFrom(), dto.getPledgeTo()));
            creditApplication.setSum(generator.getRandomNumber(dto.getSumFrom(), dto.getSumTo()));
            creditApplication.setLoanPeriod(generator.getRandomNumber(dto.getLoanPeriodFrom(), dto.getLoanPeriodTo()));
            creditApplication.setApplicationQuality(generator.probability(dto.getGoodApplicationPercent())
                                                                            ? ApplicationQuality.GOOD
                                                                            : ApplicationQuality.BAD);
            creditApplication.setScoringSystemResolution(ApplicationQuality.UNKNOWN);
            listOfCreditApplications.add(creditApplication);
            amountOfApplications--;
        }
        return listOfCreditApplications;
    }

    private List<Client> createClients(CreditApplicationGeneratorDto dto) {
        int numberOfClients = dto.getAmountOfClients();
        List<Client> clientsList = new ArrayList<>(numberOfClients);
        LocalDate today = LocalDate.now();
        while (numberOfClients > 0) {
            Client client = new Client();
//            String username = FALSE_CLIENT_DISCRIMINATOR + numberOfClients + LocalDateTime.now().toString();
            String username = numberOfClients + LocalDateTime.now().toString();
            Gender gender = generator.getRandomElement(dto.getGender());
            client.setFirstName(namesGeneratorService.getRandomFirstName(gender));
            client.setLastName(namesGeneratorService.getRandomLastName(gender));
            client.setUsername(username);
            client.setPassword(passwordEncoder.encode(username));
            client.setRoles(Collections.singletonList(new Role(1L, "USER")));
            client.setGender(gender);
            client.setBirthday(generator.getRandomDateBetween(today.minusYears(dto.getAgeFrom()),
                                                            today.minusYears(dto.getAgeTo())));
            client.setRating(generator.getRandomElement(Arrays.asList(ClientRating.values())));
            client.setMaritalStatus(generator.getRandomElement(Arrays.asList(MaritalStatus.values())));
            client.setChildren(createChildren(client, generator.getRandomNumber(dto.getChildrenFrom(),
                                                                                dto.getChildrenTo())));
            clientsList.add(client);
            numberOfClients--;
        }
        List<Client> result = clientService.save(clientsList);
        return result;
    }

    private List<Child> createChildren(Client client, int childrenNumber) {
        List<Child> children = new LinkedList<>();
        for (int i = 0; i < childrenNumber; i++) {
            Child child = new Child();
            child.setBirthday(generator.getRandomDateBetween(LocalDate.now(),
                                                            LocalDate.now().minusYears(END_OF_CHILDHOOD)));
            Gender gender = generator.getRandomElement(Arrays.asList(Gender.values()));
            child.setGender(gender);
            child.setFirstName(namesGeneratorService.getRandomFirstName(gender));
            if (child.getGender().equals(client.getGender())) {
                child.setLastName(client.getLastName());
            } else {
                child.setLastName(lastNameService.findAnotherGenderEquivalent(client.getLastName(),
                                                                                client.getGender()));
            }
            children.add(child);
        }
        return children;
    }
}
