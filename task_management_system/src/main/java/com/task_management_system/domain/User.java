package com.task_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task_management_system.enums.Gender;
import com.task_management_system.utilities.GeneralConstants;
import com.task_management_system.utilities.UniqueRefUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity(name="users")
public class User {
    private String userId = UniqueRefUtil.generateUniqueRef(GeneralConstants.USER_ID_PREFIX, 13);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @JsonIgnore
    private long id;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    private String username;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "email", length = 50)
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private Boolean isAccountEnabled;

    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private Boolean isAccountNonLocked;

    private Long failedAttempt;

    private LocalDateTime lockTime;

    private LocalDateTime dateCreated;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    private LocalDateTime lastPasswordResetDate;

    private String role;

    private String[] authorities;


    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isAccountEnabled() {
        return isAccountEnabled;
    }
}
