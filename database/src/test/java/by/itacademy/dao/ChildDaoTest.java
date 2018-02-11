package by.itacademy.dao;

import by.itacademy.entity.Child;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class ChildDaoTest extends BaseTest {

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ChildDao.getInstance().save(session, CHILD);
        Child found = ChildDao.getInstance().findById(session, 1L);

        Assert.assertEquals(found, CHILD);

        transaction.commit();
        session.close();

    }
}