package by.itacademy.service;

import by.itacademy.entity.Child;
import by.itacademy.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public void save(Child child) {
        childRepository.save(child);
    }

    @Override
    public List<Child> findAll() {
        Iterable<Child> children = childRepository.findAll();
        ArrayList<Child> result = new ArrayList<>();
        children.forEach(result::add);
        return result;
    }
}
