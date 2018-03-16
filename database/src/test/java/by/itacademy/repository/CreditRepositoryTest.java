package by.itacademy.repository;

import by.itacademy.entity.Credit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CreditRepositoryTest extends BaseTest {

    @Autowired
    private CreditRepository creditRepository;

    @Test
    public void testSaveAndLoad() {
        creditRepository.save(CREDIT_WITH_FIXED_INTEREST);
        Assert.assertNotNull(creditRepository.findOne(1L));
        creditRepository.save(CREDIT_WITH_VARIABLE_INTEREST);
        List<Credit> result = new ArrayList<>();
        creditRepository.findAll().forEach(result::add);
        Assert.assertEquals(result.size(), 2);
    }
}