package by.itacademy.service;

import by.itacademy.entity.CurrencyExchangeRate;

import java.util.List;

public interface CurrencyExchangeRateService {

    List<CurrencyExchangeRate> findAll();

    void save(CurrencyExchangeRate currencyExchangeRate);
}
