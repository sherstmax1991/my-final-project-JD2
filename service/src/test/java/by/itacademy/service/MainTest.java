package by.itacademy.service;

import by.itacademy.dto.CreditApplicationNeuronNetworkDto;
import by.itacademy.entity.Child;
import by.itacademy.entity.Role;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.Gender;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainTest extends BaseTest {

    @Test
    public void probability() {
        for (int i = 0; i < 300; i++) {
            Assert.assertFalse(!basicGenerator.probability(100));
            Assert.assertFalse(basicGenerator.probability(0));
        }
    }

    @Test
    public void getRandomNumber() {
        Set<Integer> guarantors = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            guarantors.add(basicGenerator.getRandomNumber(GUARANTORS_FROM, GUARANTORS_TO));
        }
        Assert.assertTrue(guarantors.size() == 4);
    }

    @Test
    public void getRandomElement() {
        Set<Role> methodResult = new HashSet<>();
        for (int i = 0; i < ROLES.size() * MULTIPLIER; i++) {
            methodResult.add(basicGenerator.getRandomElement(ROLES));
        }
        Assert.assertEquals(ROLES.size(), methodResult.size());
    }

    @Test
    public void getRandomDateBetween() {
        Set<LocalDate> methodResult = new HashSet<>();
        int daysBetween = (int) ChronoUnit.DAYS.between(TODAY, ONE_WEEK_AGO);
        for (int i = 0; i < daysBetween * MULTIPLIER; i++) {
            methodResult.add(basicGenerator.getRandomDateBetween(TODAY, ONE_WEEK_AGO));
        }
        Assert.assertEquals(daysBetween + 1, methodResult.size());
    }

    @Test
    public void testChildrenService() {
        List<Child> all = childService.findAll();
        Assert.assertEquals(all.size(), 1);
        Assert.assertNotNull(childService.save(CHILD).getId());
    }

    @Test
    public void testCreditService() {
        Assert.assertEquals(creditService.findAll().size(), 2);
        Assert.assertEquals(creditService.findOne(1L), CREDIT_WITH_FIXED_INTEREST_AND_ID);
        Assert.assertNotNull(creditService.save(CREDIT_WITH_FIXED_INTEREST).getId());
    }

    @Test
    public void testClientService() {
        Assert.assertEquals(clientService.findAll().size(), 1);
        Assert.assertEquals(clientService.findByUsername("top"), CLIENT_WITH_ID);
        Assert.assertTrue(clientService.save(CLIENT).getId() == 1);
        Assert.assertTrue(clientService.save(Collections.singletonList(CLIENT)).size() == 1);
    }

    @Test
    public void testFirstNameService() {
        Assert.assertTrue(firstNameService.findAllByGender(Gender.MALE).size() == 1);
        Assert.assertEquals(firstNameService.findAllByGender(Gender.MALE).get(0), FIRST_MALE_NAME);
        Assert.assertTrue(firstNameService.findAllByGender(Gender.FEMALE).size() == 1);
        Assert.assertEquals(firstNameService.findAllByGender(Gender.FEMALE).get(0), FIRST_FEMALE_NAME);
        Assert.assertTrue(firstNameService.findAll().size() == 2);
    }

    @Test
    public void testLastNameService() {
        Assert.assertEquals(lastNameService.findAll().size(), 1);
        Assert.assertEquals(lastNameService.findAll().get(0), LAST_NAME);
        Assert.assertEquals(lastNameService.findAnotherGenderEquivalent("Шерстобитова", Gender.FEMALE), "Шерстобитов");
        Assert.assertEquals(lastNameService.findByFemaleEquivalent("Шерстобитова"), LAST_NAME);
        Assert.assertEquals(lastNameService.findByMaleEquivalent("Шерстобитов"), LAST_NAME);
    }

    @Test
    public void testRolesService() {
        Assert.assertEquals(roleService.findAll(), ROLES);
        Assert.assertEquals(roleService.findByName("USER"), USER_ROLE);
    }

    @Test
    public void testCreditApplicationAnalyzer() {
        ApplicationQuality firstResult = creditApplicationAnalyzer.analyzeCreditApplication(
                                                    new CreditApplicationNeuronNetworkDto(FIRST_CREDIT_APPLICATION));
        ApplicationQuality secondResult = creditApplicationAnalyzer.analyzeCreditApplication(
                                                    new CreditApplicationNeuronNetworkDto(FIRST_CREDIT_APPLICATION));
        Assert.assertNotNull(firstResult);
        Assert.assertNotNull(secondResult);
    }

    @Test
    public void testNeuralNetworkCreationServiceImpl() {
        Assert.assertFalse(neuralNetworkCreationServiceImpl.run(NEURON_NETWORK_SETTINGS).equals(""));
    }
}

