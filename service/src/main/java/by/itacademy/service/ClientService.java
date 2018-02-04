package by.itacademy.service;

import by.itacademy.dao.ClientDao;
import by.itacademy.entity.Client;

import java.util.List;

public class ClientService {

    public List<Client> getAllClients() {
        return ClientDao.getInstance().getAllClients();
    }
}
