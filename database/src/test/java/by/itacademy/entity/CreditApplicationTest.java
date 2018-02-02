package by.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class CreditApplicationTest extends BaseTest {

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(child);
        session.save(client);
        session.save(credit);
        session.save(creditApplication);

        CreditApplication loadedApplication = session.find(CreditApplication.class, 1L);
        Assert.assertEquals(loadedApplication, creditApplication);

        transaction.commit();
        session.close();
    }
}