package by.itacademy.service;

import by.itacademy.dao.CreditDao;
import by.itacademy.entity.Credit;

import java.util.List;

public class CreditService {

    public List<Credit> getFirstCredit() {
        return new CreditDao().getCredits();
    }
}
