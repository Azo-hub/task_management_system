package com.task_management_system.services.impl;

import com.task_management_system.domain.User;
import com.task_management_system.security.UserPrincipal;
import com.task_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        System.out.println("here!");

        if (user == null) {
            throw new UsernameNotFoundException("Username not found");

        } else {
            validateUserLogin(user);
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            return userPrincipal;
        }

    }

    private void validateUserLogin(User user) {

        if (!user.isAccountNonLocked()) {

            if (userService.unlock(user)) {

            }

        }

    }

}
