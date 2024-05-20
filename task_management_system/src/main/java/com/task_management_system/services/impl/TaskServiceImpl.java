package com.task_management_system.services.impl;

import com.task_management_system.domain.Task;
import com.task_management_system.dto.TaskDto;
import com.task_management_system.enums.Priority;
import com.task_management_system.repository.TaskRepository;
import com.task_management_system.services.TaskService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            task.get().setTitle(Objects.nonNull(taskDto.getTitle()) ? taskDto.getTitle() : task.get().getTitle());
            task.get().setDescription(Objects.nonNull(taskDto.getDescription()) ? taskDto.getDescription() : task.get().getDescription());
            task.get().setPriority(Objects.nonNull(taskDto.getPriority()) ? taskDto.getPriority() : task.get().getPriority());
            task.get().setUpdatedAt(LocalDateTime.now());

        }

        return taskRepository.save(task.get());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Boolean setTaskCompletion(Long id, Boolean status) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            task.get().setCompleted(Objects.nonNull(status) ? status : task.get().isCompleted());
            taskRepository.save(task.get());
            return true;
        }

        return false;
    }









}
