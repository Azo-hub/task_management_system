package com.task_management_system.controller;

import com.task_management_system.domain.Task;
import com.task_management_system.dto.TaskDto;
import com.task_management_system.services.TaskService;
import com.task_management_system.utilities.HttpCustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Task")
@RestController
@RequestMapping("/task")
@PreAuthorize("hasAnyAuthority('user:update')")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(
            description = "Get all task endpoint",
            summary = "return list of all task",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @GetMapping
    public ResponseEntity<List<Task>> loadTask() {
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }

    @Operation(
            description = "Create task endpoint",
            summary = "returns task created",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.OK);
    }

    @Operation(
            description = "Update task endpoint",
            summary = "Update a task by the taskId",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, TaskDto taskDto) {
        return new ResponseEntity<>(taskService.updateTask(id,taskDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete task endpoint",
            summary = "Delete a task by the taskId, Only accessible by Admin role",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpCustomResponse> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return response(HttpStatus.OK, "Task deleted Successfully!");
    }

    @Operation(
            description = "Set Task Completion Endpoint",
            summary = "set task completion by taskId",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @PostMapping("/{id}")
    public ResponseEntity<HttpCustomResponse> setTaskCompletion(@PathVariable("id") Long id, @RequestParam("completeStatus") Boolean status) {
        Boolean completed = taskService.setTaskCompletion(id, status);
        if(completed){
            return response(HttpStatus.OK,"Completion Status Set Successfully");
        }
        return response(HttpStatus.NOT_IMPLEMENTED,"Failed to set completion status");
    }



    private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

        return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

    }
}
