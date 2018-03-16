package by.itacademy.service;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.CreditApplication;
import by.itacademy.repository.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;

    @Autowired
    public CreditApplicationServiceImpl(CreditApplicationRepository creditApplicationRepository) {
        this.creditApplicationRepository = creditApplicationRepository;
    }

    @Override
    public List<CreditApplication> findAll() {
        List<CreditApplication> result = new ArrayList<>();
        Iterable<CreditApplication> creditApplications = creditApplicationRepository.findAll();
        creditApplications.forEach(result::add);
        return result;
    }

    public PageableSearchResult<CreditApplication> findByParameters(CreditApplicationSearchFormDto formDto) {
         return creditApplicationRepository.findByParameters(formDto);
    }
}
