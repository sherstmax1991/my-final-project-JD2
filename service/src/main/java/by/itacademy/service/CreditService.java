package by.itacademy.service;

import by.itacademy.dao.CreditDao;
import by.itacademy.entity.Credit;

import java.util.List;

public final class CreditService {

    private CreditService(){}

    private static CreditService INSTANCE;

    public static CreditService getInstance() {
        if (INSTANCE == null) {
            synchronized (CreditService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreditService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Credit> getCredits() {
        return CreditDao.getInstance().findAll();
    }
}
