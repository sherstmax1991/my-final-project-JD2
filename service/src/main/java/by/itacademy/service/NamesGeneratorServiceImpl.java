package by.itacademy.service;

import by.itacademy.entity.FirstName;
import by.itacademy.entity.LastName;
import by.itacademy.entity.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class NamesGeneratorServiceImpl implements NamesGeneratorService {

    private static Map<Gender, List<String>> firstNames = new HashMap<>();
    private static Map<Gender, List<String>> lastNames = new HashMap<>();
    private BasicGenerator basicGenerator;
    private FirstNameService firstNameService;
    private LastNameService lastNameService;

    @Autowired
    public NamesGeneratorServiceImpl(BasicGenerator basicGenerator, FirstNameService firstNameService,
                                     LastNameService lastNameService) {
        this.basicGenerator = basicGenerator;
        this.firstNameService = firstNameService;
        this.lastNameService = lastNameService;
        List<String> firstMaleNames = firstNameService.findAllByGender(Gender.MALE).stream()
                                                    .map(FirstName::getName)
                                                    .collect(Collectors.toList());
        List<String> lastMaleNames = lastNameService.findAll().stream()
                                                    .map(LastName::getMaleEquivalent)
                                                    .collect(Collectors.toList());
        List<String> firstFemaleNames = firstNameService.findAllByGender(Gender.FEMALE).stream()
                                                    .map(FirstName::getName)
                                                    .collect(Collectors.toList());
        List<String> lastFemaleNames = lastNameService.findAll().stream()
                                                    .map(LastName::getFemaleEquivalent)
                                                    .collect(Collectors.toList());
        firstNames.put(Gender.MALE, firstMaleNames);
        firstNames.put(Gender.FEMALE, firstFemaleNames);
        lastNames.put(Gender.MALE, lastMaleNames);
        lastNames.put(Gender.FEMALE, lastFemaleNames);
    }

    public String getRandomFirstName(Gender gender) {
        return basicGenerator.getRandomElement(firstNames.get(gender));
    }

    public String getRandomLastName(Gender gender) {
        return basicGenerator.getRandomElement(lastNames.get(gender));
    }
}
