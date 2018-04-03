package by.itacademy.service;

import by.itacademy.entity.LastName;
import by.itacademy.entity.enums.Gender;
import by.itacademy.repository.LastNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LastNameServiceImpl implements LastNameService {

    private LastNameRepository lastNameRepository;

    @Autowired
    public LastNameServiceImpl(LastNameRepository lastNameRepository) {
        this.lastNameRepository = lastNameRepository;
    }

    @Override
    public LastName findByFemaleEquivalent(String femaleEquivalent) {
        return lastNameRepository.findByFemaleEquivalent(femaleEquivalent);
    }

    @Override
    public LastName findByMaleEquivalent(String maleEquivalent) {
        return lastNameRepository.findByMaleEquivalent(maleEquivalent);
    }

    @Override
    public List<LastName> findAll() {
        return lastNameRepository.findAll();
    }

    @Override
    public String findAnotherGenderEquivalent(String lastName, Gender gender) {
        if (gender.equals(Gender.MALE)) {
            return lastNameRepository.findByMaleEquivalent(lastName).getFemaleEquivalent();
        }
        return lastNameRepository.findByFemaleEquivalent(lastName).getMaleEquivalent();
    }
}
