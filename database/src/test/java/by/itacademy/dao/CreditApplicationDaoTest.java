package by.itacademy.dao;

import by.itacademy.entity.CreditApplication;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class CreditApplicationDaoTest extends BaseTest {

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(CHILD);
        session.save(CLIENT);
        session.save(CREDIT_WITH_FIXED_INTEREST);
        session.save(CREDIT_WITH_VARIABLE_INTEREST);

        CreditApplicationDao.getInstance().addCreditApplication(session, FIRST_CREDIT_APPLICATION);
        CreditApplicationDao.getInstance().addCreditApplication(session, SECOND_CREDIT_APPLICATION);

        CreditApplication firstLoadedApplication = CreditApplicationDao.getInstance().getCreditApplication(session, 1L);
        CreditApplication secondLoadedApplication = CreditApplicationDao.getInstance().getCreditApplication(session, 2L);

        Assert.assertEquals(firstLoadedApplication, FIRST_CREDIT_APPLICATION);
        Assert.assertEquals(secondLoadedApplication, SECOND_CREDIT_APPLICATION);

        transaction.commit();
        session.close();
    }
}