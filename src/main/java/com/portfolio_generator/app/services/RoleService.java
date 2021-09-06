package com.portfolio_generator.app.services;

import com.portfolio_generator.app.models.Role;
import com.portfolio_generator.app.repositories.RoleRepository;
import com.portfolio_generator.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IService<Role, RoleNotFoundException> {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(() ->
                new RoleNotFoundException("Role not found!"));
    }

    @Override
    public Role save(Role role) {

        if (role.getName().toLowerCase() != null) {
            role.setName(role.getName().toUpperCase());
        }
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteById(Long id) throws RoleNotFoundException {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException("Role not found!");
        }
        roleRepository.deleteById(id);
    }
}
