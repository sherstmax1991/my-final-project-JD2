package by.itacademy.repository;

import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long>,
                                                        CreditApplicationRepositoryCustom {

    List<CreditApplication> findAllByClient(Client client);
    List<CreditApplication> findAllByClientUsername(String username);
}