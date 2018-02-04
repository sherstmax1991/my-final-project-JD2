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

        ChildDao.getInstance().addChild(session, CHILD);

        Child loadedChild = ChildDao.getInstance().getChild(session, 1L);
        Assert.assertEquals(loadedChild, CHILD);

        transaction.commit();
        session.close();
    }
}