package com.task_management_system.dto;

import com.task_management_system.validations.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "username is required")
    private String username;

}
