package by.itacademy.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientRepositoryTest extends BaseTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testSaveAndLoad() {
        childRepository.save(CHILD);
        clientRepository.save(CLIENT);
        Assert.assertEquals(clientRepository.findOne(1L).toString(), CLIENT.toString());
    }
}