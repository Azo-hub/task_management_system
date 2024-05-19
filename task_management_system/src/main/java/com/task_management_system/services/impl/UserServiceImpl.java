package com.task_management_system.services.impl;

import com.task_management_system.domain.User;
import com.task_management_system.exceptions.EmailExistException;
import com.task_management_system.exceptions.UserNotFoundException;
import com.task_management_system.exceptions.UsernameExistException;
import com.task_management_system.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void increaseFailedAttempt(User user) {

    }

    @Override
    public void lock(User user) {

    }

    @Override
    public boolean unlock(User user) {
        return false;
    }

    @Override
    public void resetFailedAttempts(String username) {

    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User createUser(String email, String username, String role) throws UsernameExistException, UserNotFoundException, EmailExistException {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
