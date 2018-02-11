package by.itacademy.dao;

import by.itacademy.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class ClientDaoTest extends BaseTest {

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ChildDao.getInstance().save(session, CHILD);
        ClientDao.getInstance().save(session, CLIENT);
        Client found = ClientDao.getInstance().findById(session, 1L);

        Assert.assertEquals(found, CLIENT);

        transaction.commit();
        session.close();
    }
}