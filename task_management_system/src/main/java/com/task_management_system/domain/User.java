package com.task_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task_management_system.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User {
    private String userId = UniqueRefUtil.generateUniqueRef(Constants.USER_ID_PREFIX, 13);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name = "password")
    @JsonIgnore
    private String password;

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

    @Column(name = "enabled", columnDefinition="BOOLEAN DEFAULT false")
    private boolean enabled;

    @Column(name = "activated", columnDefinition="BOOLEAN DEFAULT false")
    private boolean activated;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    private LocalDateTime lastPasswordResetDate;

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
