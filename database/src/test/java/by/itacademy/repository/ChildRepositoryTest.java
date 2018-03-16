package by.itacademy.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ChildRepositoryTest extends BaseTest {

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testSaveAndLoad() {
        childRepository.save(CHILD);
        Assert.assertEquals(childRepository.findOne(1L).toString(), CHILD.toString());
    }
}