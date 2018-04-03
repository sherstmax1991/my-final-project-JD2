package by.itacademy.service;

import by.itacademy.entity.Credit;

import java.util.List;

public interface CreditService {

    List<Credit> findAll();

    Credit findOne(Long id);

    Credit save(Credit credit);
}
