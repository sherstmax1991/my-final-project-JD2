package by.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class ClientTest extends BaseTest {

    @Test
    public void testSaveAndLoad() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(child);
        session.save(client);

        Client loadedClient = session.find(Client.class, 1L);
        Assert.assertEquals(loadedClient, client);

        transaction.commit();
        session.close();
    }
}