package com.task_management_system.services.impl;

import com.task_management_system.domain.User;
import com.task_management_system.dto.LoginRequest;
import com.task_management_system.enums.Role;
import com.task_management_system.exceptions.EmailExistException;
import com.task_management_system.exceptions.UserNotFoundException;
import com.task_management_system.exceptions.UsernameExistException;
import com.task_management_system.repository.UserRepository;
import com.task_management_system.services.UserService;
import com.task_management_system.utilities.SecurityConstant;
import com.task_management_system.utilities.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUser(String email, String username, String password, String role)
            throws UsernameExistException, UserNotFoundException, EmailExistException {

        /*Validate if user do not already exist*/
        validateNewUsernameAndEmail("", username, email);

        /*Start creating a new user*/
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setIsAccountNonLocked(true);
        user.setIsAccountEnabled(true);
        user.setFailedAttempt((long) 0);
        user.setDateCreated(LocalDateTime.now());
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);


        if (!role.trim().isEmpty() && role.equals("USER")) {

            user.setRole(Role.ROLE_USER.name());
            user.setAuthorities(Role.ROLE_USER.getAuthorities());

        }

        if (!role.trim().isEmpty() && role.equals("ADMIN")) {
            user.setRole(Role.ROLE_ADMIN.name());
            user.setAuthorities(Role.ROLE_ADMIN.getAuthorities());

        }

        userRepository.save(user);

        return user;

    }

    @Override
    public void increaseFailedAttempt(User user) {
        long newFailedAttempts = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempt(newFailedAttempts, user.getUsername());

    }

    @Override
    public void lock(User user) {
        user.setIsAccountNonLocked(false);
        user.setLockTime(new Date());
        userRepository.save(user);

    }

    @Override
    public boolean unlock(User user) {

        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + SecurityConstant.LOGIN_LOCK_TIME_DURATION < currentTimeInMillis) {

            user.setIsAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt((long) 0);

            userRepository.save(user);

            return true;
        }

        return false;

    }

    @Override
    public void resetFailedAttempts(String username) {
        userRepository.updateFailedAttempt(0, username);

    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }



    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String email)
            throws UsernameExistException, UserNotFoundException, EmailExistException {

        User userByNewUsername = findByUsername(newUsername);
        User userByEmail = findByEmail(email);

        if (!currentUsername.trim().isEmpty()) {

            User localUser = findByUsername(currentUsername);

            if (localUser == null) {

                throw new UserNotFoundException("No user found by username " + currentUsername);
            }

            if (userByNewUsername != null && !localUser.getId().equals(userByNewUsername.getId())) {

                throw new UsernameExistException("Username already exists");

            }

            if (userByEmail != null && !localUser.getId().equals(userByEmail.getId())) {

                throw new EmailExistException("Email already exists");

            }

            return localUser;

        } else {

            if (userByNewUsername != null) {

                throw new UsernameExistException("Username already exists");

            }

            if (userByEmail != null) {

                throw new EmailExistException("Email already exists");

            }

            return null;

        }
    }

}
