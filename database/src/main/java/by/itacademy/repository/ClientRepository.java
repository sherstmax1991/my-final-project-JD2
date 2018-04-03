package by.itacademy.repository;

import by.itacademy.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUsername(String name);

//    void deleteAllByUsernameStartingWith(String discriminator);

    void deleteAllByIfRealIsFalse();
}
