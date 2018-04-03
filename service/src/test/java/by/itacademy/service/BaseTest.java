package by.itacademy.service;

import by.itacademy.dto.NeuronNetworkSettingsDto;
import by.itacademy.entity.Child;
import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.CreditWithFixedInterest;
import by.itacademy.entity.CreditWithVariableInterest;
import by.itacademy.entity.CurrencyExchangeRate;
import by.itacademy.entity.FirstName;
import by.itacademy.entity.LastName;
import by.itacademy.entity.Role;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Currency;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import by.itacademy.repository.ChildRepository;
import by.itacademy.repository.ClientRepository;
import by.itacademy.repository.CreditApplicationRepository;
import by.itacademy.repository.CreditRepository;
import by.itacademy.repository.CurrencyExchangeRatesRepository;
import by.itacademy.repository.FirstNameRepository;
import by.itacademy.repository.LastNameRepository;
import by.itacademy.repository.RolesRepository;
import by.itacademy.service.neuronNetworkService.CreditApplicationAnalyzerImpl;
import by.itacademy.service.neuronNetworkService.NeuralNetworkCreationServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {

    private static final String TRAINING_SET_FILE_NAME =
            "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//dataset.txt";
    private static final String NEURON_NETWORK_PATH =
            "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//neuronNetwork.nnet";
    protected static List<CurrencyExchangeRate> EXCHANGE_RATES = new ArrayList<>();
    protected static LocalDate ONE_WEEK_AGO = LocalDate.now().plusWeeks(1);
    protected static LastName LAST_NAME = new LastName("Шерстобитов", "Шерстобитова");
    protected static FirstName FIRST_MALE_NAME = new FirstName("Максим", Gender.MALE);
    protected static FirstName FIRST_FEMALE_NAME = new FirstName("Вацлава", Gender.FEMALE);
    protected static Client CLIENT = new Client();
    protected static NeuronNetworkSettingsDto NEURON_NETWORK_SETTINGS = new NeuronNetworkSettingsDto();
    protected static Integer[] LAYERS = {12, 6};
    protected static Client CLIENT_WITH_ID = new Client();
    protected static Child CHILD = new Child();
    protected static Child CHILD_WITH_ID = new Child();
    protected static CreditWithFixedInterest CREDIT_WITH_FIXED_INTEREST = new CreditWithFixedInterest();
    protected static CreditWithFixedInterest CREDIT_WITH_FIXED_INTEREST_AND_ID = new CreditWithFixedInterest();
    protected static CreditWithVariableInterest CREDIT_WITH_VARIABLE_INTEREST = new CreditWithVariableInterest();
    protected static CreditApplication FIRST_CREDIT_APPLICATION = new CreditApplication();
    protected static CreditApplication SECOND_CREDIT_APPLICATION = new CreditApplication();
    protected static Role USER_ROLE = new Role(1L, "USER");
    protected static Role ADMIN_ROLE = new Role(2L, "ADMIN");
    protected static Role GOD_ROLE = new Role(3L, "GOD");
    protected static List<Role> ROLES = new LinkedList<>();
    protected static final int GUARANTORS_FROM = 0;
    protected static final int GUARANTORS_TO = 3;
    protected static final int MULTIPLIER = 10;

    protected static final LocalDate TODAY = LocalDate.now();
    protected static int CHILD_AGE = 3;
    protected static int CLIENT_AGE = 27;

    static {
        ROLES.addAll(Arrays.asList(USER_ROLE, ADMIN_ROLE, GOD_ROLE));

        EXCHANGE_RATES.add(new CurrencyExchangeRate(1L, Currency.EUR, 1.9, 1.95, 1));
        EXCHANGE_RATES.add(new CurrencyExchangeRate(2L, Currency.USD, 2.2, 2.3, 1));

        CHILD.setFirstName("Руслан");
        CHILD.setLastName("Шерстобитов");
        CHILD.setGender(Gender.MALE);
        CHILD.setBirthday(TODAY.minusYears(CHILD_AGE));

        CHILD_WITH_ID.setFirstName(CHILD.getFirstName());
        CHILD_WITH_ID.setLastName(CHILD.getLastName());
        CHILD_WITH_ID.setGender(CHILD.getGender());
        CHILD_WITH_ID.setBirthday(CHILD.getBirthday());
        CHILD_WITH_ID.setId(1L);

        CLIENT.setFirstName("Максим");
        CLIENT.setLastName("Шерстобитов");
        CLIENT.setGender(Gender.MALE);
        CLIENT.setRating(ClientRating.GOOD);
        CLIENT.setMaritalStatus(MaritalStatus.SINGLE);
        CLIENT.setUsername("top");
        CLIENT.setPassword("s3cr3t");
        CLIENT.setRoles(Arrays.asList(USER_ROLE, ADMIN_ROLE, GOD_ROLE));
        CLIENT.setBirthday(TODAY.minusYears(CLIENT_AGE));
        CLIENT.setChildren(Collections.singletonList(CHILD));
        CLIENT.setIfReal(true);
        CHILD.getParents().add(CLIENT);

        CLIENT_WITH_ID.setId(1L);
        CLIENT_WITH_ID.setFirstName(CLIENT.getFirstName());
        CLIENT_WITH_ID.setLastName(CLIENT.getLastName());
        CLIENT_WITH_ID.setGender(CLIENT.getGender());
        CLIENT_WITH_ID.setRating(CLIENT.getRating() );
        CLIENT_WITH_ID.setMaritalStatus(CLIENT.getMaritalStatus());
        CLIENT_WITH_ID.setUsername(CLIENT.getUsername());
        CLIENT_WITH_ID.setPassword(CLIENT.getPassword());
        CLIENT_WITH_ID.setRoles(CLIENT.getRoles());
        CLIENT_WITH_ID.setBirthday(CLIENT.getBirthday());
        CLIENT_WITH_ID.setChildren(CLIENT.getChildren());
        CLIENT.setIfReal(true);
        CHILD_WITH_ID.getParents().add(CLIENT_WITH_ID);

        CREDIT_WITH_FIXED_INTEREST.setTitle("Super Fixed Loan");
        CREDIT_WITH_FIXED_INTEREST.setGuarantors(2);
        CREDIT_WITH_FIXED_INTEREST.setInterestRate(14D);

        CREDIT_WITH_FIXED_INTEREST_AND_ID.setTitle(CREDIT_WITH_FIXED_INTEREST.getTitle());
        CREDIT_WITH_FIXED_INTEREST_AND_ID.setGuarantors(CREDIT_WITH_FIXED_INTEREST.getGuarantors());
        CREDIT_WITH_FIXED_INTEREST_AND_ID.setInterestRate(CREDIT_WITH_FIXED_INTEREST.getInterestRate());
        CREDIT_WITH_FIXED_INTEREST_AND_ID.setId(1L);

        CREDIT_WITH_VARIABLE_INTEREST.setTitle("Unstable credit");
        CREDIT_WITH_VARIABLE_INTEREST.setGuarantors(1);
        CREDIT_WITH_VARIABLE_INTEREST.setInterestRateIncrement(5.5D);

        FIRST_CREDIT_APPLICATION.setClient(CLIENT);
        FIRST_CREDIT_APPLICATION.setCredit(CREDIT_WITH_FIXED_INTEREST);
        FIRST_CREDIT_APPLICATION.setApplicationDate(TODAY);
        FIRST_CREDIT_APPLICATION.setIncome(800);
        FIRST_CREDIT_APPLICATION.setLoanPeriod(24);
        FIRST_CREDIT_APPLICATION.setPledge(0);
        FIRST_CREDIT_APPLICATION.setSum(5000);
        FIRST_CREDIT_APPLICATION.setApplicationQuality(ApplicationQuality.UNKNOWN);
        FIRST_CREDIT_APPLICATION.setScoringSystemResolution(ApplicationQuality.GOOD);

        SECOND_CREDIT_APPLICATION.setClient(CLIENT);
        SECOND_CREDIT_APPLICATION.setCredit(CREDIT_WITH_VARIABLE_INTEREST);
        SECOND_CREDIT_APPLICATION.setApplicationDate(TODAY);
        SECOND_CREDIT_APPLICATION.setIncome(800);
        SECOND_CREDIT_APPLICATION.setLoanPeriod(12);
        SECOND_CREDIT_APPLICATION.setPledge(1000);
        SECOND_CREDIT_APPLICATION.setSum(2000);
        SECOND_CREDIT_APPLICATION.setApplicationQuality(ApplicationQuality.GOOD);
        SECOND_CREDIT_APPLICATION.setScoringSystemResolution(ApplicationQuality.BAD);

        NEURON_NETWORK_SETTINGS.setTrainSetPercent(70);
        NEURON_NETWORK_SETTINGS.setTestSetPercent(30);
        NEURON_NETWORK_SETTINGS.setLearningRate(0.01);
        NEURON_NETWORK_SETTINGS.setMaxError(0.001);
        NEURON_NETWORK_SETTINGS.setMaxIterations(10);
        NEURON_NETWORK_SETTINGS.setTrainSetFilePath(
                "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//dataset.txt");
        NEURON_NETWORK_SETTINGS.setNeuronNetworkFilePath(
                "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//neuronNetwork.nnet");
        NEURON_NETWORK_SETTINGS.setLayers(LAYERS);
        NEURON_NETWORK_SETTINGS.setTrainSetFilePath(TRAINING_SET_FILE_NAME);
        NEURON_NETWORK_SETTINGS.setNeuronNetworkFilePath(NEURON_NETWORK_PATH);
    }

    @Mock
    protected PasswordEncoder passwordEncoder;

    @Mock
    protected BasicGeneratorImpl basicGenerator;

    @Mock
    protected ChildRepository childRepository;

    @Mock
    protected ClientRepository clientRepository;

    @Mock
    protected CreditRepository creditRepository;

    @Mock
    protected RolesRepository rolesRepository;

    @Mock
    protected CreditApplicationRepository creditApplicationRepository;

    @Mock
    protected FirstNameRepository firstNameRepository;

    @Mock
    protected LastNameRepository lastNameRepository;

    @Mock
    protected CurrencyExchangeRatesRepository currencyExchangeRatesRepository;

    @InjectMocks
    protected FirstNameServiceImpl firstNameService;

    @InjectMocks
    protected LastNameServiceImpl lastNameService;

    @InjectMocks
    protected ChildServiceImpl childService;

    @InjectMocks
    protected CreditServiceImpl creditService;

    @InjectMocks
    protected ClientServiceImpl clientService;

    @InjectMocks
    protected RoleServiceImpl roleService;

    @InjectMocks
    protected NeuralNetworkCreationServiceImpl neuralNetworkCreationServiceImpl;

    @InjectMocks
    protected CreditApplicationAnalyzerImpl creditApplicationAnalyzer;

    @InjectMocks
    protected CurrencyExchangeRateServiceImpl currencyExchangeRateService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(basicGenerator.probability(100))
                .thenCallRealMethod();
        Mockito.when(basicGenerator.probability(0))
                .thenCallRealMethod();
        Mockito.when(basicGenerator.getRandomNumber(GUARANTORS_FROM, GUARANTORS_TO))
                .thenCallRealMethod();
        Mockito.when(basicGenerator.getRandomElement(ROLES))
                .thenCallRealMethod();
        Mockito.when(basicGenerator.getRandomDateBetween(TODAY, ONE_WEEK_AGO))
                .thenCallRealMethod();

        Mockito.when(currencyExchangeRatesRepository.findAll())
                .thenReturn(EXCHANGE_RATES);

        Mockito.when(lastNameRepository.findAll())
                .thenReturn(Collections.singletonList(LAST_NAME));
        Mockito.when(lastNameRepository.findByMaleEquivalent("Шерстобитов"))
                .thenReturn(LAST_NAME);
        Mockito.when(lastNameRepository.findByFemaleEquivalent("Шерстобитова"))
                .thenReturn(LAST_NAME);

        Mockito.when(firstNameRepository.findAllByGender(Gender.MALE))
                .thenReturn(Collections.singletonList(FIRST_MALE_NAME));
        Mockito.when(firstNameRepository.findAllByGender(Gender.FEMALE))
                .thenReturn(Collections.singletonList(FIRST_FEMALE_NAME));
        Mockito.when(firstNameRepository.findAll())
                .thenReturn(Arrays.asList(FIRST_FEMALE_NAME, FIRST_MALE_NAME));

        Mockito.when(childRepository.findAll())
                .thenReturn(Collections.singletonList(CHILD));
        Mockito.when(childRepository.save(CHILD))
                .thenReturn(CHILD_WITH_ID);
        Mockito.when(childRepository.save(Collections.singletonList(CHILD)))
                .thenReturn(Collections.singletonList(CHILD_WITH_ID));

        Mockito.when(creditRepository.findAll())
                .thenReturn(Arrays.asList(CREDIT_WITH_FIXED_INTEREST, CREDIT_WITH_VARIABLE_INTEREST));
        Mockito.when(creditRepository.save(CREDIT_WITH_FIXED_INTEREST))
                .thenReturn(CREDIT_WITH_FIXED_INTEREST_AND_ID);
        Mockito.when(creditRepository.findOne(1L))
                .thenReturn(CREDIT_WITH_FIXED_INTEREST_AND_ID);

        Mockito.when(passwordEncoder.encode(CLIENT.getPassword()))
                .thenReturn(CLIENT.getPassword());

        Mockito.when(clientRepository.findAll())
                .thenReturn(Collections.singletonList(CLIENT));
        Mockito.when(clientRepository.findByUsername("top"))
                .thenReturn(CLIENT_WITH_ID);
        Mockito.when(clientRepository.save(CLIENT))
                .thenReturn(CLIENT_WITH_ID);
        Mockito.when(clientRepository.save(Collections.singletonList(CLIENT)))
                .thenReturn(Collections.singletonList(CLIENT_WITH_ID));

        Mockito.when(rolesRepository.findByName("USER"))
                .thenReturn(USER_ROLE);
        Mockito.when(rolesRepository.findAll())
                .thenReturn(ROLES);
    }
}