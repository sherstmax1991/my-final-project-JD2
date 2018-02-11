package by.itacademy.service;

import by.itacademy.dao.ChildDao;
import by.itacademy.dao.ClientDao;
import by.itacademy.entity.Client;

import java.util.List;

public final class ClientService {

    private ClientService(){}

    private static ClientService INSTANCE;

    public static ClientService getInstance() {
        if (INSTANCE == null) {
            synchronized (ChildDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Client> getAllClients() {
        return ClientDao.getInstance().findAll();
    }
}
