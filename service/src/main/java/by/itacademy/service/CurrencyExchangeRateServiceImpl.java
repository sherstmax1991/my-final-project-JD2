package by.itacademy.service;

import by.itacademy.entity.CurrencyExchangeRate;
import by.itacademy.repository.CurrencyExchangeRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "exchangeRates")
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

    private final CurrencyExchangeRatesRepository currencyExchangeRatesRepository;

    @Autowired
    public CurrencyExchangeRateServiceImpl(CurrencyExchangeRatesRepository currencyCoursesRepository) {
        this.currencyExchangeRatesRepository = currencyCoursesRepository;
    }

    @Override
    @Cacheable
    public List<CurrencyExchangeRate> findAll() {
        return currencyExchangeRatesRepository.findAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(CurrencyExchangeRate currencyExchangeRate) {
        currencyExchangeRatesRepository.save(currencyExchangeRate);
    }
}
