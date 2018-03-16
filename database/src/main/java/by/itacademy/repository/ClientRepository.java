package by.itacademy.repository;

import by.itacademy.entity.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client, Long> {
}
