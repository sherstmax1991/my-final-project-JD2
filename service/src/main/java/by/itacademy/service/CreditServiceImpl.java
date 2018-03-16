package by.itacademy.service;

import by.itacademy.entity.Credit;
import by.itacademy.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public List<Credit> findAll() {
        Iterable<Credit> credits = creditRepository.findAll();
        ArrayList<Credit> result = new ArrayList<>();
        credits.forEach(result::add);
        return result;
    }
}
