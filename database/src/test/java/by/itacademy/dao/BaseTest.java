package by.itacademy.dao;

import by.itacademy.entity.Child;
import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.CreditWithFixedInterest;
import by.itacademy.entity.CreditWithVariableInterest;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.time.LocalDate;

public abstract class BaseTest {

    protected static SessionFactory SESSION_FACTORY;
    protected static final Client CLIENT = new Client();
    protected static final Child CHILD = new Child();
    protected static final CreditWithFixedInterest CREDIT_WITH_FIXED_INTEREST = new CreditWithFixedInterest();
    protected static final CreditWithVariableInterest CREDIT_WITH_VARIABLE_INTEREST = new CreditWithVariableInterest();
    protected static final CreditApplication FIRST_CREDIT_APPLICATION = new CreditApplication();
    protected static final CreditApplication SECOND_CREDIT_APPLICATION = new CreditApplication();

    private static final LocalDate TODAY = LocalDate.now();
    private static final int CHILD_AGE = 3;
    private static final int CLIENT_AGE = 27;

    static {
        CHILD.setFirstName("Руслан");
        CHILD.setLastName("Шерстобитов");
        CHILD.setGender(Gender.MALE);
        CHILD.setBirthday(TODAY.minusYears(CHILD_AGE));

        CLIENT.setFirstName("Максим");
        CLIENT.setLastName("Шерстобитов");
        CLIENT.setGender(Gender.MALE);
        CLIENT.setRating(ClientRating.GOOD);
        CLIENT.setMaritalStatus(MaritalStatus.SINGLE);
        CLIENT.setLogin("s3cr3t");
        CLIENT.setBirthday(TODAY.minusYears(CLIENT_AGE));
        CLIENT.getChildren().add(CHILD);
        CHILD.getParents().add(CLIENT);

        CREDIT_WITH_FIXED_INTEREST.setTitle("Super Fixed Loan");
        CREDIT_WITH_FIXED_INTEREST.setGuarantors(2);
        CREDIT_WITH_FIXED_INTEREST.setInterestRate(14D);

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
    }

    @BeforeClass
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void finish() {
        SESSION_FACTORY.close();
    }
}