package by.itacademy.dao;

import by.itacademy.entity.Credit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditDaoTest {

    private static final SessionFactory SESSION_FACTORY
            = new Configuration().configure().buildSessionFactory();

    @Test
    public void testSaveToDb() {
        Session session = SESSION_FACTORY.openSession();
        Credit credit = new Credit("Super Loan", 2, 13.0);
        session.save(credit);

        Credit foundEmployee = session.find(Credit.class, 1L);
        assertThat(foundEmployee.getTitle(), is("Super Loan"));
        assertThat(foundEmployee.getGuarantors(), is(2));
        assertThat(foundEmployee.getInterestRate(), is("13.0"));
        session.close();
    }

    @AfterClass
    public static void finish() {
        SESSION_FACTORY.close();
    }
}
