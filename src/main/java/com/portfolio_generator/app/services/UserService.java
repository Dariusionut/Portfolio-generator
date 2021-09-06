package com.portfolio_generator.app.services;

import com.portfolio_generator.app.exceptions.UserNotFoundException;
import com.portfolio_generator.app.models.User;
import com.portfolio_generator.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User, UserNotFoundException> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {

        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
    }

    public User findByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    public List<User> searchBy(String name) {
        List<User> results;
        if (name != null && (name.trim().length() > 0)) {
            results = userRepository.findByUsernameContainsOrFirstNameContainsOrLastNameContainingAllIgnoreCase(name, name, name);
        } else {
            results = findAll();
        }
        return results;
    }

    @Override
    public User save(User user) {
        if (user.getUserDetails().getId() != null) {
            user.updateDetails(user.getUserDetails().getId(), user.getUserDetails().getInfo());
        } else {
            user.addDetails(user.getUserDetails().getInfo());
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.saveAndFlush(user);
    }


    @Override
    public void deleteById(Long id) throws UserNotFoundException {
        Optional<User> schoolById = userRepository.findById(id);
        if (schoolById.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        userRepository.deleteById(id);
    }

}
