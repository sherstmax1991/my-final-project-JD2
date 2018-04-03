package by.itacademy.service;

import by.itacademy.entity.LastName;
import by.itacademy.entity.enums.Gender;

import java.util.List;

public interface LastNameService {

    LastName findByFemaleEquivalent(String femaleEquivalent);

    LastName findByMaleEquivalent(String maleEquivalent);

    List<LastName> findAll();

    String findAnotherGenderEquivalent(String lastName, Gender gender);
}
