package by.itacademy.dao;

import by.itacademy.entity.Credit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreditDao {

    public List<Credit> getCredits() {
        SessionFactory sessionFactory = new Configuration()
                .configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        List<Credit> credits = session.createQuery("from Credit ", Credit.class).list();

        session.close();
        sessionFactory.close();
        return credits;
    }
}
