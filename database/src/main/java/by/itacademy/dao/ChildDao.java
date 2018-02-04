package by.itacademy.dao;

import by.itacademy.entity.Child;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.List;

public final class ChildDao {

    private static ChildDao INSTANCE;

    private ChildDao() {
    }

    public static ChildDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ChildDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ChildDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Child> getAllChildren() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<Child> children = session.createQuery("from Child ", Child.class).list();
        session.close();
        sessionFactory.close();
        return children;
    }

    public List<Child> getAllChildren(Session session) {
        return session.createQuery("from Child ", Child.class).list();
    }

    public Child getChild(Session session, Long id) {
        return session.get(Child.class, id);
    }

    public void addChild(Session session, Child child) {
        session.save(child);
    }

    public void addAllChildren(Session session, Collection<Child> children) {
        for (Child child : children) {
            session.save(child);
        }
    }
}