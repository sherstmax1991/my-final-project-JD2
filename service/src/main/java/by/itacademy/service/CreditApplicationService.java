package by.itacademy.service;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.CreditApplication;

import java.util.List;

public interface CreditApplicationService {

    List<CreditApplication> findAll();

    PageableSearchResult<CreditApplication> findByParameters(CreditApplicationSearchFormDto formDto);
}
