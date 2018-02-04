package by.itacademy.service;

import by.itacademy.dao.CreditApplicationDao;
import by.itacademy.entity.CreditApplication;

import java.util.List;

public class CreditApplicationService {

    public List<CreditApplication> getAllCreditApplications() {
        return CreditApplicationDao.getInstance().getAllCreditApplications();
    }
}
