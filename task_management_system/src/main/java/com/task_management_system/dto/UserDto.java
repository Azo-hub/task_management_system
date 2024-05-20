package com.task_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task_management_system.validations.annotations.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @ValidPassword
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "username is required")
    private String username;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "role is required")
    private String role;

}
