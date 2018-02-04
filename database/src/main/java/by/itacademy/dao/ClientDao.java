package by.itacademy.dao;

import by.itacademy.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.List;

public final class ClientDao {

    private static ClientDao INSTANCE;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Client> getAllClients() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client ", Client.class).list();
        session.close();
        sessionFactory.close();
        return clients;
    }

    public List<Client> getAllClients(Session session) {
        return session.createQuery("from Client ", Client.class).list();
    }

    public Client getClient(Session session, Long id) {
        return session.get(Client.class, id);
    }

    public void addClient(Session session, Client client) {
        session.save(client);
    }

    public void addAllClients(Session session, Collection<Client> clients) {
        for (Client client : clients) {
            session.save(client);
        }
    }
}