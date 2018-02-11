package by.itacademy.dao;

import by.itacademy.entity.Child;

public final class ChildDao extends BaseDao<Child> {

    private static ChildDao INSTANCE;

    private ChildDao() {
    }

    public static ChildDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ChildDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ChildDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected Class<Child> getEntityClass() {
        return Child.class;
    }
}