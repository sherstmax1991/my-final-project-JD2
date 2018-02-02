package by.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class CreditTest extends BaseTest{

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(credit);

        Credit loadedCredit = session.find(Credit.class, 1L);
        Assert.assertEquals(loadedCredit, credit);

        transaction.commit();
        session.close();
    }
}