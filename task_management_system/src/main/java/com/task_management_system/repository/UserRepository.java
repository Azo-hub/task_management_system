package com.task_management_system.repository;

import com.task_management_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Query("UPDATE Userr u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempt(long failedAttempt, String username);

}
