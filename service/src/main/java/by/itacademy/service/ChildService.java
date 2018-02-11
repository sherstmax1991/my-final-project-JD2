package by.itacademy.service;

import by.itacademy.dao.ChildDao;
import by.itacademy.entity.Child;

import java.util.List;

public final class ChildService {

    private ChildService(){}

    private static ChildService INSTANCE;

    public static ChildService getInstance() {
        if (INSTANCE == null) {
            synchronized (ChildDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ChildService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Child> getChildren() {
        return ChildDao.getInstance().findAll();
    }
}
