package by.itacademy.service;

import by.itacademy.dao.ChildDao;
import by.itacademy.entity.Child;

import java.util.List;

public class ChildService {

    public List<Child> getChildren() {
        return ChildDao.getInstance().getAllChildren();
    }
}
