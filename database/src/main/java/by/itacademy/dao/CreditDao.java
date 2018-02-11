package by.itacademy.dao;

import by.itacademy.entity.Credit;

public final class CreditDao extends BaseDao<Credit> {

    private static CreditDao INSTANCE;

    private CreditDao() {
    }

    public static CreditDao getInstance() {
        if (INSTANCE == null) {
            synchronized (CreditDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreditDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected Class<Credit> getEntityClass() {
        return Credit.class;
    }
}
