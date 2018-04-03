package by.itacademy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class BasicGeneratorImpl implements BasicGenerator {

    @Override
    public boolean probability(int percent) {
        return new Random().nextInt(100) + 1 <= percent;
    }

    @Override
    public int getRandomNumber(int from, int to) {
        return from < to ? new Random().nextInt(to - from + 1) + from : new Random().nextInt(from - to + 1) + to;
    }

    @Override
    public <T> T getRandomElement(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    @Override
    public LocalDate getRandomDateBetween(LocalDate dateFrom, LocalDate dateTo) {
        LocalDate earliestDate = dateFrom;
        LocalDate latestDate = dateTo;
        if (dateFrom.isAfter(dateTo)) {
            earliestDate = dateTo;
            latestDate = dateFrom;
        }
        int daysBetween = (int) ChronoUnit.DAYS.between(earliestDate, latestDate);
        int randomDayNumber = new Random().nextInt(daysBetween + 1);
        return earliestDate.plusDays(randomDayNumber);
    }
}
