package by.itacademy.service;

import by.itacademy.entity.Child;

import java.util.List;

public interface ChildService {

    void save(Child child);

    List<Child> findAll();
}
