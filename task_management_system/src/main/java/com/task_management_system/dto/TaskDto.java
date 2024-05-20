package com.task_management_system.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDto {

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "priority is required")
    private String priority;


}
