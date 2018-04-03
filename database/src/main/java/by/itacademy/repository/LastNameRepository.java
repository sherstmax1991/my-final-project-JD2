package by.itacademy.repository;

import by.itacademy.entity.LastName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastNameRepository extends JpaRepository<LastName, Long> {

    LastName findByFemaleEquivalent(String femaleEquivalent);

    LastName findByMaleEquivalent(String maleEquivalent);
}