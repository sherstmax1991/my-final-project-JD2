package by.itacademy.service;

import by.itacademy.dao.CreditApplicationDao;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;

import java.time.LocalDate;
import java.util.List;

public class CreditApplicationService {

    private static CreditApplicationService INSTANCE;

    public static CreditApplicationService getInstance() {
        if (INSTANCE == null) {
            synchronized (CreditApplicationService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreditApplicationService();
                }
            }
        }
        return INSTANCE;
    }

    public List<CreditApplication> findByParameters(LocalDate dateFrom, LocalDate dateTo, Integer ageFrom, Integer
                                                    ageTo, Integer childrenFrom, Integer childrenTo, Gender gender,
                                                    MaritalStatus maritalStatus, ClientRating clientRating, Long
                                                    creditId, Integer incomeFrom, Integer incomeTo, Integer pledgeFrom,
                                                    Integer pledgeTo, Integer sumFrom, Integer sumTo, Integer
                                                    loanPeriodFrom, Integer loanPeriodTo, ApplicationQuality
                                                    applicationQuality, ApplicationQuality scoringSystemResolution,
                                                    Integer page, Integer applicationsPerPage) {

        return CreditApplicationDao.getInstance().findByParameters(dateFrom, dateTo, ageFrom, ageTo, childrenFrom,
                childrenTo, gender, maritalStatus, clientRating, creditId, incomeFrom, incomeTo, pledgeFrom,
                pledgeTo, sumFrom, sumTo, loanPeriodFrom, loanPeriodTo, applicationQuality, scoringSystemResolution,
                page, applicationsPerPage);
    }
}
