package by.itacademy.repository;

import by.itacademy.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
