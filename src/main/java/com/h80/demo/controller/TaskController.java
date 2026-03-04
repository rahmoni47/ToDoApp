package com.h80.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h80.demo.dto.CreateTaskRequest;
import com.h80.demo.dto.TaskResponse;
import com.h80.demo.dto.UpdateTaskRequest;
import com.h80.demo.dto.UpdateTaskStatusRequest;
import com.h80.demo.exception.ErrorMessage;
import com.h80.demo.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller REST pour la gestion des Task
 */
@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
@Tag(name = "Tasks endpoints", description = "this is the endpoints for managing all tasks")
public class TaskController {

        @Autowired
        private TaskService taskService;

        /**
         * Récupérer tous les tasks avec pagination
         */
        @GetMapping
        @Operation(summary = "get Tasks", description = "Get All tasks with pagenation")
        public ResponseEntity<Page<TaskResponse>> getAllTasks(Pageable pageable) {
                Page<TaskResponse> tasks = taskService.findAll(pageable);
                return ResponseEntity.ok(tasks);
        }

        /**
         * Récupérer un task par son ID
         */
        @GetMapping("/{id}")
        @Operation(summary = "get Task", description = "Get a specific task with id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "404", description = "there is no task with this id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "200", description = "a task with it all details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class)))
        })
        public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
                Optional<TaskResponse> task = taskService.findById(id);
                return task.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        /**
         * Créer un nouveau task
         */
        @PostMapping
        @Operation(summary = "create task", description = "Create a new task")
        public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
                TaskResponse createdTask = taskService.create(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }

        /**
         * Mettre à jour un task existant
         */
        @Operation(summary = "update Task", description = "update Task from it's id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "404", description = "there is no task with this id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "200", description = "task info updated successfully",content=@Content(mediaType="application/json",schema=@Schema(implementation=TaskResponse.class)))
        })
        @PutMapping("/{id}")
        public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id,
                        @Valid @RequestBody UpdateTaskRequest request) {
                Optional<TaskResponse> updatedTask = taskService.update(id, request);
                return updatedTask.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        /**
         * Supprimer un task
         */
        @DeleteMapping("/{id}")
        @Operation(summary = "delete task", description = "delete existing task")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "404", description = "there is no task with this id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "204", description = "task deleted successfully")
        })
        public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
                boolean deleted = taskService.delete(id);
                return deleted ? ResponseEntity.noContent().build()
                                : ResponseEntity.notFound().build();
        }

        /**
         * Rechercher des tasks
         */
        @GetMapping(value =  "/search",produces = "application/json")
        @Operation(summary = "search task", description = "search for tasks with pagination")
        public ResponseEntity<Page<TaskResponse>> searchTasks(@RequestParam String query,
                        Pageable pageable) {
                Page<TaskResponse> results = taskService.search(query, pageable);
                return ResponseEntity.ok(results);
        }

        @PatchMapping("/status/{id}")
        @Operation(summary = "update status", description = "update status for given task")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "status updated successfully",content=@Content(mediaType="application/json",schema=@Schema(implementation=TaskResponse.class))),
                        @ApiResponse(responseCode = "404", description = "there is no task with this id", content = @Content(mediaType = "application/json", schema =@Schema(implementation=ErrorMessage.class)))
        })
        public ResponseEntity<?> updateStatus(@PathVariable Long id,
                        @Valid @RequestBody UpdateTaskStatusRequest request) {
                Optional<TaskResponse> updatedTask = taskService.updateTaskStatus(request, id);
                return updatedTask.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }
}