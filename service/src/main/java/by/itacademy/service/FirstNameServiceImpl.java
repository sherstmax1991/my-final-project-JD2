package by.itacademy.service;

import by.itacademy.entity.FirstName;
import by.itacademy.entity.enums.Gender;
import by.itacademy.repository.FirstNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FirstNameServiceImpl implements FirstNameService {

    private FirstNameRepository firstNameRepository;

    @Autowired
    public FirstNameServiceImpl(FirstNameRepository firstNameRepository) {
        this.firstNameRepository = firstNameRepository;
    }

    @Override
    public List<FirstName> findAll() {
        return firstNameRepository.findAll();
    }

    @Override
    public List<FirstName> findAllByGender(Gender gender) {
        return firstNameRepository.findAllByGender(gender);
    }
}
