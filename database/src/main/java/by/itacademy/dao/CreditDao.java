package by.itacademy.dao;

import by.itacademy.entity.Credit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.List;

public final class CreditDao {

    private static CreditDao INSTANCE;

    private CreditDao() {
    }

    public static CreditDao getInstance() {
        if (INSTANCE == null) {
            synchronized (CreditDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreditDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Credit> getAllCredits() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<Credit> credits = session.createQuery("from Credit ", Credit.class).list();
        session.close();
        sessionFactory.close();
        return credits;
    }

    public List<Credit> getAllCredits(Session session) {
        return session.createQuery("from Credit ", Credit.class).list();
    }

    public Credit getCredit(Session session, Long id) {
        return session.get(Credit.class, id);
    }

    public void addCredit(Session session, Credit credit) {
        session.save(credit);
    }

    public void addAllCredits(Session session, Collection<Credit> credits) {
        for (Credit credit : credits) {
            session.save(credit);
        }
    }
}
