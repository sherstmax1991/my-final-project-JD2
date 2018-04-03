package by.itacademy.service;

import by.itacademy.entity.Role;
import by.itacademy.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    private RolesRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Cacheable
    public List<Role> findAll() {
        Iterable<Role> roles = rolesRepository.findAll();
        ArrayList<Role> result = new ArrayList<>();
        roles.forEach(result::add);
        return result;
    }
//
//    @Override
//    @Cacheable(key = "#root.args[0]")
//    public Role findOne(Long id) {
//        return rolesRepository.findOne(id);
//    }

    @Override
    @Cacheable(key = "#root.args[0]")
    public Role findByName(String name) {
        return rolesRepository.findByName(name);
    }
}
