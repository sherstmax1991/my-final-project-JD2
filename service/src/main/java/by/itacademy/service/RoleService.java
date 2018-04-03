package by.itacademy.service;

import by.itacademy.entity.Role;

import java.util.List;


public interface RoleService {


    List<Role> findAll();

//    Role findOne(Long id);

    Role findByName(String name);
}
