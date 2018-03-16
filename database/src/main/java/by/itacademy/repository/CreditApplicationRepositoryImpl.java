package by.itacademy.repository;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.CreditApplication;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class CreditApplicationRepositoryImpl implements CreditApplicationRepositoryCustom {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CreditApplicationRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public PageableSearchResult<CreditApplication> findByParameters(CreditApplicationSearchFormDto formDto) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        LocalDate birthdayFrom = LocalDate.now().minusYears(formDto.getAgeTo());
        LocalDate birthdayTo = LocalDate.now().minusYears(formDto.getAgeFrom());
        Integer applicationsPerPage = formDto.getApplicationsPerPage();

        List<CreditApplication> result = entityManager.createQuery("select app from CreditApplication app where "
                            + "(app.applicationDate between :dateFrom and :dateTo) and "
                            + "(app.client.birthday between :birthdayFrom and :birthdayTo) and "
                            + "(size(app.client.children) between :childrenFrom and :childrenTo) and "
                            + "(app.client.gender in (:gender)) and "
                            + "(app.client.maritalStatus in (:maritalStatus)) and "
                            + "(app.client.rating in (:clientRating)) and "
                            + "(app.credit.id in (:credits)) and "
                            + "(app.income between :incomeFrom and :incomeTo) and "
                            + "(app.pledge between :pledgeFrom and :pledgeTo) and "
                            + "(app.sum between :sumFrom and :sumTo) and "
                            + "(app.loanPeriod between :loanPeriodFrom and :loanPeriodTo) and "
                            + "(app.applicationQuality in (:applicationQuality)) and "
                            + "(app.scoringSystemResolution in (:scoringSystemResolution))", CreditApplication.class)
                            .setParameter("dateFrom", formDto.getDateFrom())
                            .setParameter("dateTo", formDto.getDateTo())
                            .setParameter("birthdayFrom", birthdayFrom)
                            .setParameter("birthdayTo", birthdayTo)
                            .setParameter("childrenFrom", formDto.getChildrenFrom())
                            .setParameter("childrenTo", formDto.getChildrenTo())
                            .setParameter("gender", formDto.getGender())
                            .setParameter("maritalStatus", formDto.getMaritalStatus())
                            .setParameter("clientRating", formDto.getClientRating())
                            .setParameter("incomeFrom", formDto.getIncomeFrom())
                            .setParameter("incomeTo", formDto.getIncomeTo())
                            .setParameter("pledgeFrom", formDto.getPledgeFrom())
                            .setParameter("pledgeTo", formDto.getPledgeTo())
                            .setParameter("sumFrom", formDto.getSumFrom())
                            .setParameter("sumTo", formDto.getSumTo())
                            .setParameter("loanPeriodFrom", formDto.getLoanPeriodFrom())
                            .setParameter("loanPeriodTo", formDto.getLoanPeriodTo())
                            .setParameter("applicationQuality", formDto.getApplicationQuality())
                            .setParameter("scoringSystemResolution", formDto.getScoringSystemResolution())
                            .setParameter("credits", formDto.getCreditId())
                            .setFirstResult((formDto.getPage() - 1) * applicationsPerPage)
                            .setMaxResults(applicationsPerPage).getResultList();

        Long elementsAmount = entityManager.createQuery("select count(app) from CreditApplication app where "
                            + "(app.applicationDate between :dateFrom and :dateTo) and "
                            + "(app.client.birthday between :birthdayFrom and :birthdayTo) and "
                            + "(size(app.client.children) between :childrenFrom and :childrenTo) and "
                            + "(app.client.gender in (:gender)) and "
                            + "(app.client.maritalStatus in (:maritalStatus)) and "
                            + "(app.client.rating in (:clientRating)) and "
                            + "(app.credit.id in (:credits)) and "
                            + "(app.income between :incomeFrom and :incomeTo) and "
                            + "(app.pledge between :pledgeFrom and :pledgeTo) and "
                            + "(app.sum between :sumFrom and :sumTo) and "
                            + "(app.loanPeriod between :loanPeriodFrom and :loanPeriodTo) and "
                            + "(app.applicationQuality in (:applicationQuality)) and "
                            + "(app.scoringSystemResolution in (:scoringSystemResolution))", Long.class)
                            .setParameter("dateFrom", formDto.getDateFrom())
                            .setParameter("dateTo", formDto.getDateTo())
                            .setParameter("birthdayFrom", birthdayFrom)
                            .setParameter("birthdayTo", birthdayTo)
                            .setParameter("childrenFrom", formDto.getChildrenFrom())
                            .setParameter("childrenTo", formDto.getChildrenTo())
                            .setParameter("gender", formDto.getGender())
                            .setParameter("maritalStatus", formDto.getMaritalStatus())
                            .setParameter("clientRating", formDto.getClientRating())
                            .setParameter("incomeFrom", formDto.getIncomeFrom())
                            .setParameter("incomeTo", formDto.getIncomeTo())
                            .setParameter("pledgeFrom", formDto.getPledgeFrom())
                            .setParameter("pledgeTo", formDto.getPledgeTo())
                            .setParameter("sumFrom", formDto.getSumFrom())
                            .setParameter("sumTo", formDto.getSumTo())
                            .setParameter("loanPeriodFrom", formDto.getLoanPeriodFrom())
                            .setParameter("loanPeriodTo", formDto.getLoanPeriodTo())
                            .setParameter("applicationQuality", formDto.getApplicationQuality())
                            .setParameter("scoringSystemResolution", formDto.getScoringSystemResolution())
                            .setParameter("credits", formDto.getCreditId())
                            .getSingleResult();

        Long lastPageNumber = elementsAmount % applicationsPerPage == 0
                                ? elementsAmount / applicationsPerPage
                                : elementsAmount / applicationsPerPage + 1;

        transaction.commit();
        return new PageableSearchResult<>(result, lastPageNumber);
    }
}