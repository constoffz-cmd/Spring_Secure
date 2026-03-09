package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.RoleServiceInter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService implements RoleServiceInter {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> findRolesByIds(List<Long> ids) {
        return new HashSet<>(roleRepository.findAllById(ids));
    }
}