package com.task_management_system.services;

import com.task_management_system.domain.Task;
import com.task_management_system.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();
    Task createTask(TaskDto taskDto);
    Task updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
    Boolean setTaskCompletion(Long id, Boolean status);
}
