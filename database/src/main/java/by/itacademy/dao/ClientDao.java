package by.itacademy.dao;

import by.itacademy.entity.Client;
import lombok.ToString;

@ToString
public final class ClientDao extends BaseDao<Client> {

    private static ClientDao INSTANCE;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }
}