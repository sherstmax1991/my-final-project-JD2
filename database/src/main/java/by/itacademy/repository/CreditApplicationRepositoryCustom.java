package by.itacademy.repository;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.PageableSearchResult;
import by.itacademy.entity.CreditApplication;

public interface CreditApplicationRepositoryCustom {

    PageableSearchResult<CreditApplication> findByParameters(CreditApplicationSearchFormDto formDto);
}
