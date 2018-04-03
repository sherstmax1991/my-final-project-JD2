package by.itacademy.service;

import by.itacademy.entity.Child;
import by.itacademy.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChildServiceImpl implements ChildService {

    private ChildRepository childRepository;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public List<Child> findAll() {
        return childRepository.findAll();
    }

    @Override
    public Child save(Child child) {
        return childRepository.save(child);
    }

    @Override
    public void deleteAllByIfRealIsFalse() {
        childRepository.deleteAllByIfRealIsFalse();
    }
}
