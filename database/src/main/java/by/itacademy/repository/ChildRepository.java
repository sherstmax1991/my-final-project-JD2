package by.itacademy.repository;

import by.itacademy.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {

    void deleteAllByIfRealIsFalse();
}
