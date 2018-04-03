package by.itacademy.service;

import by.itacademy.entity.enums.Gender;

public interface NamesGeneratorService {

    String getRandomFirstName(Gender gender);

    String getRandomLastName(Gender gender);
}
