package by.itacademy.dao;

import by.itacademy.entity.CreditApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.List;

public final class CreditApplicationDao {

    private static CreditApplicationDao INSTANCE;

    private CreditApplicationDao() {
    }

    public static CreditApplicationDao getInstance() {
        if (INSTANCE == null) {
            synchronized (CreditApplicationDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreditApplicationDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<CreditApplication> getAllCreditApplications() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<CreditApplication> creditApplications = session.createQuery("from CreditApplication ", CreditApplication.class)
                .list();
        session.close();
        sessionFactory.close();
        return creditApplications;
    }

    public List<CreditApplication> getAllCreditApplications(Session session) {
        return session.createQuery("from CreditApplication", CreditApplication.class).list();
    }

    public CreditApplication getCreditApplication(Session session, Long id) {
        return session.get(CreditApplication.class, id);
    }

    public void addCreditApplication(Session session, CreditApplication creditApplication) {
        session.save(creditApplication);
    }

    public void addAllCreditApplication(Session session, Collection<CreditApplication> creditApplications) {
        for (CreditApplication creditApplication : creditApplications) {
            session.save(creditApplication);
        }
    }
}