package by.itacademy.service;

import by.itacademy.dto.CreditApplicationGeneratorDto;
import by.itacademy.entity.CreditApplication;

import java.util.List;

public interface CreditApplicationGeneratorService {

    List<CreditApplication> createCreditApplications(CreditApplicationGeneratorDto creditApplicationGeneratorDto);
}
