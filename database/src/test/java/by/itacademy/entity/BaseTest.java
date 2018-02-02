package by.itacademy.entity;

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
    protected static final Client client = new Client();
    protected static final Child child = new Child();
    protected static final Credit credit = new Credit();
    protected static final CreditApplication creditApplication = new CreditApplication();
    protected static final LocalDate today = LocalDate.now();
    private static final int CHILD_AGE = 3;
    private static final int CLIENT_AGE = 27;

    static {
        child.setFirstName("Руслан");
        child.setLastName("Шерстобитов");
        child.setGender(Gender.MALE);
        child.setBirthday(today.minusYears(CHILD_AGE));

        client.setFirstName("Максим");
        client.setLastName("Шерстобитов");
        client.setGender(Gender.MALE);
        client.setClientRating(ClientRating.GOOD);
        client.setMaritalStatus(MaritalStatus.SINGLE);
        client.setLogin("s3cr3t");
        client.setBirthday(today.minusYears(CLIENT_AGE));
        client.getChildren().add(child);

        credit.setTitle("Super Loan");
        credit.setGuarantors(2);
        credit.setInterestRate(14D);

        creditApplication.setClient(client);
        creditApplication.setCredit(credit);
        creditApplication.setApplicationDate(today);
        creditApplication.setIncome(800);
        creditApplication.setLoanPeriod(24);
        creditApplication.setPledge(0);
        creditApplication.setSum(5000);
        creditApplication.setApplicationQuality(ApplicationQuality.UNKNOWN);
        creditApplication.setScoringSystemResolution(ApplicationQuality.GOOD);
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