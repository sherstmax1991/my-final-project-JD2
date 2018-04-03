package by.itacademy.repository;

import by.itacademy.entity.CurrencyExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRatesRepository extends JpaRepository<CurrencyExchangeRate, Long> {
}
