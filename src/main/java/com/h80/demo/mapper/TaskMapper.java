package com.h80.demo.mapper;

import com.h80.demo.entity.Task;
import com.h80.demo.dto.CreateTaskRequest;
import com.h80.demo.dto.UpdateTaskRequest;
import com.h80.demo.dto.UpdateTaskStatusRequest;
import com.h80.demo.dto.TaskResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper pour convertir entre Task et ses DTOs
 */
@Component
public class TaskMapper {

    /**
     * Convertir une entité vers un DTO de réponse
     */
    public TaskResponse toResponse(Task entity) {
        if (entity == null) {
            return null;
        }

        TaskResponse response = new TaskResponse();
        response.setId(entity.getId());
        response.setTask(entity.getTask());
        response.setDate(entity.getDate());
        response.setDone(entity.isDone());
        response.setDescription(entity.getDescription());
        
        return response;
    }

    /**
     * Convertir un DTO de création vers une entité
     */
    public Task toEntity(CreateTaskRequest request) {
        if (request == null) {
            return null;
        }

        Task entity = new Task();
        entity.setTask(request.getTask());
        entity.setDescription(request.getDescription());
        
        return entity;
    }

    /**
     * Mettre à jour une entité existante avec les données d'un DTO de mise à jour
     */
    public void updateEntityFromRequest(UpdateTaskRequest request, Task entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getTask() != null) {
            entity.setTask(request.getTask());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
    }

    /**
     * Convertir une liste d'entités vers une liste de DTOs de réponse
     */
    public java.util.List<TaskResponse> toResponseList(java.util.List<Task> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Convertir une liste de DTOs de création vers une liste d'entités
     */
    public java.util.List<Task> toEntityList(java.util.List<CreateTaskRequest> requests) {
        if (requests == null) {
            return null;
        }

        return requests.stream()
                .map(this::toEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Copier les propriétés d'une entité vers une autre (utile pour les mises à jour partielles)
     */
    public void copyNonNullProperties(Task source, Task target) {
        if (source == null || target == null) {
            return;
        }

        if (source.getTask() != null) {
            target.setTask(source.getTask());
        }
        if (source.getDate() != null) {
            target.setDate(source.getDate());
        }
    }

    /**
     * Mapper partiel - ne met à jour que les champs non-null du request
     */
    public Task partialUpdate(UpdateTaskRequest request, Task existingEntity) {
        if (request == null) {
            return existingEntity;
        }

        if (existingEntity == null) {
            existingEntity = new Task();
        }

        if (request.getTask() != null && !request.getTask().trim().isEmpty()) {
            existingEntity.setTask(request.getTask());
        }
        return existingEntity;
    }
    
    public void UpdateTaskStatus(UpdateTaskStatusRequest request,Task task) {
        task.setDone(request.isDone());
    }
}