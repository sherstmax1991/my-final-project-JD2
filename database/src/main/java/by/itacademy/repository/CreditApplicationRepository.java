package by.itacademy.repository;

import by.itacademy.entity.CreditApplication;
import org.springframework.data.repository.CrudRepository;

public interface CreditApplicationRepository extends CrudRepository<CreditApplication, Long>,
                                                        CreditApplicationRepositoryCustom {

}