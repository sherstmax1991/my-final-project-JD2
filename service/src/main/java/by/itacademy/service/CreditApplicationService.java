package by.itacademy.service;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.Client;
import by.itacademy.entity.CreditApplication;

import java.util.List;

public interface CreditApplicationService {

    List<CreditApplication> findAll();

    CreditApplication save(CreditApplication creditApplication);

    List<CreditApplication> save(List<CreditApplication> creditApplications);

    CreditApplication findById(Long id);

    List<CreditApplication> findAllByClient(Client client);

    List<CreditApplication> findAllByClientUsername(String username);

    PageableSearchResult<CreditApplication> findByParameters(CreditApplicationSearchFormDto formDto);
}
