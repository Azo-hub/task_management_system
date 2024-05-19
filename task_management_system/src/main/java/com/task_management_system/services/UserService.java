package com.task_management_system.services;

import com.task_management_system.domain.User;
import com.task_management_system.exceptions.EmailExistException;
import com.task_management_system.exceptions.UserNotFoundException;
import com.task_management_system.exceptions.UsernameExistException;

import java.util.Optional;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void increaseFailedAttempt(User user);

    void lock(User user);

    boolean unlock(User user);

    void resetFailedAttempts(String username);

    Optional<User> findUserById(Long id);

    User createUser(String email, String username, String role)
            throws UsernameExistException, UserNotFoundException, EmailExistException;

    void deleteUserById(Long userId);

}
