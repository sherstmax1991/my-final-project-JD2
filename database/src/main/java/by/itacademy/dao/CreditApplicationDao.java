package by.itacademy.dao;

import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public final class CreditApplicationDao extends BaseDao<CreditApplication> {

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

    public List<CreditApplication> findByParameters(Session session, LocalDate dateFrom, LocalDate dateTo, Integer ageFrom, Integer
                                                    ageTo, Integer childrenFrom, Integer childrenTo, Gender gender,
                                                    MaritalStatus maritalStatus, ClientRating clientRating, Long
                                                    creditId, Integer incomeFrom, Integer incomeTo, Integer pledgeFrom,
                                                    Integer pledgeTo, Integer sumFrom, Integer sumTo, Integer
                                                    loanPeriodFrom, Integer loanPeriodTo, ApplicationQuality
                                                    applicationQuality, ApplicationQuality scoringSystemResolution,
                                                    Integer page, Integer applicationsPerPage) {

        LocalDate birthdayFrom = LocalDate.now().minusYears(ageTo);
        LocalDate birthdayTo = LocalDate.now().minusYears(ageFrom);

        return session.createQuery("select app from CreditApplication app where "
                                + "(app.applicationDate between :dateFrom and :dateTo) and "
                                + "(app.client.birthday between :birthdayFrom and :birthdayTo) and "
                                + "(size(app.client.children) between :childrenFrom and :childrenTo) and "
                                + "(app.client.gender = :gender) and "
                                + "(app.client.maritalStatus = :maritalStatus) and "
                                + "(app.client.rating = :clientRating) and "
                                + "(app.income between :incomeFrom and :incomeTo) and "
                                + "(app.pledge between :pledgeFrom and :pledgeTo) and "
                                + "(app.sum between :sumFrom and :sumTo) and "
                                + "(app.loanPeriod between :loanPeriodFrom and :loanPeriodTo) and "
                                + "(app.applicationQuality = :applicationQuality) and "
                                + "(app.scoringSystemResolution = :scoringSystemResolution) and "
                                + "(app.credit.id = :creditId)", CreditApplication.class)
                                .setParameter("dateFrom", dateFrom)
                                .setParameter("dateTo", dateTo)
                                .setParameter("birthdayFrom", birthdayFrom)
                                .setParameter("birthdayTo", birthdayTo)
                                .setParameter("childrenFrom", childrenFrom)
                                .setParameter("childrenTo", childrenTo)
                                .setParameter("gender", gender)
                                .setParameter("maritalStatus", maritalStatus)
                                .setParameter("clientRating", clientRating)
                                .setParameter("incomeFrom", incomeFrom)
                                .setParameter("incomeTo", incomeTo)
                                .setParameter("pledgeFrom", pledgeFrom)
                                .setParameter("pledgeTo", pledgeTo)
                                .setParameter("sumFrom", sumFrom)
                                .setParameter("sumTo", sumTo)
                                .setParameter("loanPeriodFrom", loanPeriodFrom)
                                .setParameter("loanPeriodTo", loanPeriodTo)
                                .setParameter("applicationQuality", applicationQuality)
                                .setParameter("scoringSystemResolution", scoringSystemResolution)
                                .setParameter("creditId", creditId)
                                .setFirstResult((page - 1) * applicationsPerPage)
                                .setMaxResults(applicationsPerPage)
                                .getResultList();
    }

    public List<CreditApplication> findByParameters(LocalDate dateFrom, LocalDate dateTo, Integer ageFrom, Integer
                                                    ageTo, Integer childrenFrom, Integer childrenTo, Gender gender,
                                                    MaritalStatus maritalStatus, ClientRating clientRating, Long
                                                    creditId, Integer incomeFrom, Integer incomeTo, Integer pledgeFrom,
                                                    Integer pledgeTo, Integer sumFrom, Integer sumTo, Integer
                                                    loanPeriodFrom, Integer loanPeriodTo, ApplicationQuality
                                                    applicationQuality, ApplicationQuality scoringSystemResolution,
                                                    Integer page, Integer applicationsPerPage) {

        LocalDate birthdayFrom = LocalDate.now().minusYears(ageTo);
        LocalDate birthdayTo = LocalDate.now().minusYears(ageFrom);

        Session defaultSession = SESSION_FACTORY.openSession();
        defaultSession.beginTransaction();

        List<CreditApplication> result = defaultSession.createQuery("select app from CreditApplication app where "
                                + "(app.applicationDate between :dateFrom and :dateTo) and "
                                + "(app.client.birthday between :birthdayFrom and :birthdayTo) and "
                                + "(size(app.client.children) between :childrenFrom and :childrenTo) and "
                                + "(app.client.gender = :gender) and "
                                + "(app.client.maritalStatus = :maritalStatus) and "
                                + "(app.client.rating = :clientRating) and "
                                + "(app.income between :incomeFrom and :incomeTo) and "
                                + "(app.pledge between :pledgeFrom and :pledgeTo) and "
                                + "(app.sum between :sumFrom and :sumTo) and "
                                + "(app.loanPeriod between :loanPeriodFrom and :loanPeriodTo) and "
                                + "(app.applicationQuality = :applicationQuality) and "
                                + "(app.scoringSystemResolution = :scoringSystemResolution) and "
                                + "(app.credit.id = :creditId)", CreditApplication.class)
                                .setParameter("dateFrom", dateFrom)
                                .setParameter("dateTo", dateTo)
                                .setParameter("birthdayFrom", birthdayFrom)
                                .setParameter("birthdayTo", birthdayTo)
                                .setParameter("childrenFrom", childrenFrom)
                                .setParameter("childrenTo", childrenTo)
                                .setParameter("gender", gender)
                                .setParameter("maritalStatus", maritalStatus)
                                .setParameter("clientRating", clientRating)
                                .setParameter("incomeFrom", incomeFrom)
                                .setParameter("incomeTo", incomeTo)
                                .setParameter("pledgeFrom", pledgeFrom)
                                .setParameter("pledgeTo", pledgeTo)
                                .setParameter("sumFrom", sumFrom)
                                .setParameter("sumTo", sumTo)
                                .setParameter("loanPeriodFrom", loanPeriodFrom)
                                .setParameter("loanPeriodTo", loanPeriodTo)
                                .setParameter("applicationQuality", applicationQuality)
                                .setParameter("scoringSystemResolution", scoringSystemResolution)
                                .setParameter("creditId", creditId)
                                .setFirstResult((page - 1) * applicationsPerPage)
                                .setMaxResults(applicationsPerPage)
                                .getResultList();

        defaultSession.getTransaction().commit();
        defaultSession.close();
        return result;
    }

    @Override
    protected Class<CreditApplication> getEntityClass() {
        return CreditApplication.class;
    }
}