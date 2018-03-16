package by.itacademy.repository;

import by.itacademy.entity.CreditApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CreditApplicationRepositoryTest extends BaseTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @Test
    public void testSaveAndLoad() {
        childRepository.save(CHILD);
        clientRepository.save(CLIENT);
        creditRepository.save(CREDIT_WITH_FIXED_INTEREST);
        creditRepository.save(CREDIT_WITH_VARIABLE_INTEREST);
        creditApplicationRepository.save(FIRST_CREDIT_APPLICATION);
        creditApplicationRepository.save(SECOND_CREDIT_APPLICATION);

        List<CreditApplication> result = new ArrayList<>();
        creditApplicationRepository.findAll().forEach(result::add);
        Assert.assertEquals(result.size(), 2);

//        new CreditApplicationSearchFormDto(LocalDate.now(), LocalDate.now(), 26, 28, 1, 1, Arrays.asList(MALE),
//                Arrays.asList(SINGLE), Arrays.asList(GOOD), Arrays.asList(1L, 2L), 800, 800,
//                0, 0, 4000, 6000, 12, 24,
//                Arrays.asList(ApplicationQuality.UNKNOWN), Arrays.asList(ApplicationQuality.GOOD), 1, 1)
//
//        PageableSearchResult<CreditApplication> pageResult = creditApplicationRepository.findByParameters();
//        Assert.assertEquals(pageResult.getResultPage().size(), 1);
//        Assert.assertEquals(pageResult.getLastPage().longValue(), 1L);
    }
}