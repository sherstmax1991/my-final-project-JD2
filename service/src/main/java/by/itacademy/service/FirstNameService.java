package by.itacademy.service;

import by.itacademy.entity.FirstName;
import by.itacademy.entity.enums.Gender;

import java.util.List;

public interface FirstNameService {

    List<FirstName> findAll();

    List<FirstName> findAllByGender(Gender gender);
}
