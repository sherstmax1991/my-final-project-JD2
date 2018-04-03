package by.itacademy.service;

import java.time.LocalDate;
import java.util.List;

public interface BasicGenerator {

    boolean probability(int percent);

    int getRandomNumber(int from, int to);

    <T> T getRandomElement(List<T> list);

    LocalDate getRandomDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
