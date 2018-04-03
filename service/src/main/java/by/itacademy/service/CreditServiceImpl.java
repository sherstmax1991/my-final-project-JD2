package by.itacademy.service;

import by.itacademy.entity.Credit;
import by.itacademy.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "credits")
public class CreditServiceImpl implements CreditService {

    private CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    @Cacheable
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    @Cacheable
    public Credit findOne(Long id) {
        Credit result = creditRepository.findOne(id);
        return result;
    }

    @Override
    @CacheEvict
    public Credit save(Credit credit) {
        return creditRepository.save(credit);
    }
}