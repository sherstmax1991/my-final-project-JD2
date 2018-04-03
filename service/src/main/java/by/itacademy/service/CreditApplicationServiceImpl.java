package by.itacademy.service;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;
import by.itacademy.repository.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return creditApplicationRepository.findAll();
    }

    @Override
    public CreditApplication save(CreditApplication creditApplication) {
        return creditApplicationRepository.save(creditApplication);
    }

    @Override
    public List<CreditApplication> save(List<CreditApplication> creditApplications) {
        return creditApplicationRepository.save(creditApplications);
    }

    @Override
    public CreditApplication findById(Long id) {
        return creditApplicationRepository.findOne(id);
    }

    @Override
    public List<CreditApplication> findAllByClient(Client client) {
        return creditApplicationRepository.findAllByClient(client);
    }

    @Override
    public List<CreditApplication> findAllByClientUsername(String username) {
        return creditApplicationRepository.findAllByClientUsername(username);
    }

    @Override
    public PageableSearchResult<CreditApplication> findByParameters(CreditApplicationSearchFormDto formDto) {
         return creditApplicationRepository.findByParameters(formDto);
    }
}
