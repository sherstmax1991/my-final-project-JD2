package by.itacademy.dao;

import by.itacademy.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class BaseDao<T extends BaseEntity> {

    protected static final SessionFactory SESSION_FACTORY
            = new Configuration().configure().buildSessionFactory();

    private Class<T> entityClass;

    public Long save(Session session, T objectToSave) {
        session.save(objectToSave);
        return objectToSave.getId();
    }

    public Long save(T objectToSave) {
        Session defaultSession = SESSION_FACTORY.openSession();
        defaultSession.beginTransaction();

        defaultSession.save(objectToSave);

        defaultSession.getTransaction().commit();
        defaultSession.close();

        return objectToSave.getId();
    }

    public T findById(Session session, Long id) {
        return session.get(getEntityClass(), id);
    }

    public T findById(Long id) {
        Session defaultSession = SESSION_FACTORY.openSession();
        defaultSession.beginTransaction();

        T t = defaultSession.get(getEntityClass(), id);

        defaultSession.getTransaction().commit();
        defaultSession.close();

        return t;
    }

    public List<T> findAll(Session session) {
        return session.createQuery("select t from " + getEntityClass().getSimpleName() + " t",
                getEntityClass()).getResultList();
    }

    public List<T> findAll() {
        Session defaultSession = SESSION_FACTORY.openSession();
        defaultSession.beginTransaction();

        List<T> result = defaultSession.createQuery("select t from " + getEntityClass().getSimpleName() + " t",
                getEntityClass()).getResultList();

        defaultSession.getTransaction().commit();
        defaultSession.close();

        return result;
    }

    public void delete(Session session, T t) {
        session.delete(t);
    }

    public void update(Session session, T t) {
        session.update(t);
    }

    protected abstract Class<T> getEntityClass();
}
