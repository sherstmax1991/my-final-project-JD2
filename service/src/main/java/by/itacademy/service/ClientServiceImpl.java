package by.itacademy.service;

import by.itacademy.entity.Client;
import by.itacademy.entity.Role;
import by.itacademy.repository.ChildRepository;
import by.itacademy.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

//    private static final String FALSE_CLIENT_DISCRIMINATOR = "FALSE_CLIENT";
    private PasswordEncoder passwordEncoder;
    private ClientRepository clientRepository;
    private ChildRepository childRepository;

    @Autowired
    public ClientServiceImpl(PasswordEncoder passwordEncoder, ClientRepository clientRepository, ChildRepository childRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.childRepository = childRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByUsername(String name) {
        return clientRepository.findByUsername(name);
    }

    @Override
    public Client save(Client client) {
        childRepository.save(client.getChildren());
        if (client.getId() == null) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        }
        return clientRepository.save(client);
    }

    @Override
    public List<Client> save(List<Client> clients) {
        List<Client> result = new LinkedList<>();
        for (Client client : clients) {
            childRepository.save(client.getChildren());
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            result.add(clientRepository.save(client));
        }
        return result;
    }

//    @Override
//    public void deleteAllByUsernameStartingWith() {
//        clientRepository.deleteAllByUsernameStartingWith(FALSE_CLIENT_DISCRIMINATOR);
//    }

    @Override
    public void deleteAllByIfRealIsFalse() {
        clientRepository.deleteAllByIfRealIsFalse();
        childRepository.deleteAllByIfRealIsFalse();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        return new User(username, client.getPassword(), generateAuthorities(client.getRoles()));
    }

    private Collection<? extends GrantedAuthority> generateAuthorities(List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
