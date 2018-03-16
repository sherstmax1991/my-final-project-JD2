package by.itacademy.service;

import by.itacademy.entity.Client;
import by.itacademy.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        Iterable<Client> clients = clientRepository.findAll();
        ArrayList<Client> result = new ArrayList<>();
        clients.forEach(result::add);
        return result;
    }
}
