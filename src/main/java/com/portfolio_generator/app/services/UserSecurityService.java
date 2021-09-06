package com.portfolio_generator.app.services;

import com.portfolio_generator.app.models.User;
import com.portfolio_generator.app.repositories.UserRepository;
import com.portfolio_generator.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new CustomUserDetails(user);
    }

}
