package com.task_management_system.domain;

import com.task_management_system.utilities.SchemaConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity(name= SchemaConstants.ENTITY_TASK)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String description;

    private String priority;
    private LocalDateTime dueDate;
    private boolean completed = false;
    private String createdAt;
    private String updatedAt;
    private LocalDateTime completedAt;

}
