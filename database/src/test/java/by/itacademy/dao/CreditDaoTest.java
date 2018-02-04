package by.itacademy.dao;

import by.itacademy.entity.Credit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class CreditDaoTest extends BaseTest{

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        CreditDao.getInstance().addCredit(session, CREDIT_WITH_FIXED_INTEREST);
        CreditDao.getInstance().addCredit(session, CREDIT_WITH_VARIABLE_INTEREST);

        Credit firstLoaded = CreditDao.getInstance().getCredit(session, 1L);
        Credit secondLoaded = CreditDao.getInstance().getCredit(session, 2L);

        Assert.assertEquals(firstLoaded, CREDIT_WITH_FIXED_INTEREST);
        Assert.assertEquals(secondLoaded, CREDIT_WITH_VARIABLE_INTEREST);

        transaction.commit();
        session.close();
    }
}