package by.itacademy.dao;

import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class CreditApplicationDaoTest extends BaseTest {

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ChildDao.getInstance().save(session, CHILD);
        ClientDao.getInstance().save(session, CLIENT);
        CreditDao.getInstance().save(session, CREDIT_WITH_FIXED_INTEREST);
        CreditDao.getInstance().save(session, CREDIT_WITH_VARIABLE_INTEREST);

        CreditApplicationDao.getInstance().save(session, FIRST_CREDIT_APPLICATION);
        CreditApplicationDao.getInstance().save(session, SECOND_CREDIT_APPLICATION);

        CreditApplication firstLoadedApplication = CreditApplicationDao.getInstance().findById(session, 1L);
        CreditApplication secondLoadedApplication = CreditApplicationDao.getInstance().findById(session, 2L);

        Assert.assertEquals(firstLoadedApplication, FIRST_CREDIT_APPLICATION);
        Assert.assertEquals(secondLoadedApplication, SECOND_CREDIT_APPLICATION);

        secondLoadedApplication.setSum(1000);
        CreditApplicationDao.getInstance().update(session, secondLoadedApplication);
        Integer currentSum = CreditApplicationDao.getInstance().findById(session, 2L).getSum();
        Assert.assertTrue(currentSum == 1000);

        CreditApplicationDao.getInstance().delete(session, secondLoadedApplication);
        Assert.assertTrue(CreditApplicationDao.getInstance().findAll(session).size() == 1);
        System.out.println(CreditApplicationDao.getInstance().findAll(session));
        System.out.println(CREDIT_WITH_FIXED_INTEREST.getId());
        Assert.assertEquals(CreditApplicationDao.getInstance().findByParameters(session, LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1),26, 28, 0, 2, Gender.MALE,
                MaritalStatus.SINGLE, ClientRating.GOOD, CREDIT_WITH_FIXED_INTEREST.getId(), 800,
                800, 0, 0, 5000, 5000,24, 24,
                ApplicationQuality.UNKNOWN, ApplicationQuality.GOOD, 1,2).get(0),
                FIRST_CREDIT_APPLICATION);

        transaction.commit();
        session.close();
    }
}