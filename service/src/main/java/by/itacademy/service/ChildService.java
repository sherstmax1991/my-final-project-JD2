package by.itacademy.service;

import by.itacademy.entity.Child;

import java.util.List;

public interface ChildService {

    List<Child> findAll();

    Child save(Child child);

    void deleteAllByIfRealIsFalse();
}
