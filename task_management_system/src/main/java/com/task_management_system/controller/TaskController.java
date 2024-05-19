package com.task_management_system.controller;

import com.task_management_system.domain.Task;
import com.task_management_system.dto.TaskDto;
import com.task_management_system.services.TaskService;
import com.task_management_system.utilities.HttpCustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> loadTask() {
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, TaskDto taskDto) {
        return new ResponseEntity<>(taskService.updateTask(id,taskDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpCustomResponse> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return response(HttpStatus.OK, "Task deleted Successfully!");
    }



    private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

        return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

    }
}
