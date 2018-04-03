package by.itacademy.repository;

import by.itacademy.entity.FirstName;
import by.itacademy.entity.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirstNameRepository extends JpaRepository<FirstName, Long> {

    List<FirstName> findAllByGender(Gender gender);
}
