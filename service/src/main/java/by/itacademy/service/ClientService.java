package by.itacademy.service;

import by.itacademy.entity.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {

    List<Client> findAll();

    Client findByUsername(String name);

    Client save(Client client);

    List<Client> save(List<Client> clients);

//    void deleteAllByUsernameStartingWith();

    void deleteAllByIfRealIsFalse();
}
