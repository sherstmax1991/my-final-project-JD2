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

        session.save(CHILD);
        ClientDao.getInstance().addClient(session, CLIENT);

        Client loadedClient = ClientDao.getInstance().getClient(session, 1L);
        Assert.assertEquals(loadedClient, CLIENT);

        transaction.commit();
        session.close();
    }
}