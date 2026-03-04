package com.h80.demo.service;

import com.h80.demo.repository.TaskRepository;
import com.h80.demo.entity.Task;
import com.h80.demo.dto.CreateTaskRequest;
import com.h80.demo.dto.UpdateTaskRequest;
import com.h80.demo.dto.UpdateTaskStatusRequest;
import com.h80.demo.dto.TaskResponse;
import com.h80.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Service pour la gestion des Task
 */
@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TaskMapper taskMapper;

    /**
     * Récupérer tous les tasks avec pagination
     */
    @Transactional(readOnly = true)
    public Page<TaskResponse> findAll(Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.map(taskMapper::toResponse);
    }

    /**
     * Récupérer un task par son ID
     */
    @Transactional(readOnly = true)
    public Optional<TaskResponse> findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(taskMapper::toResponse);
    }

    /**
     * Créer un nouveau task
     */
    public TaskResponse create(CreateTaskRequest request) {
        Task task = taskMapper.toEntity(request);
        
        Task savedTask = taskRepository.save(task);
        
        return taskMapper.toResponse(savedTask);
    }

    /**
     * Mettre à jour un task existant
     */
    public Optional<TaskResponse> update(Long id, UpdateTaskRequest request) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    taskMapper.updateEntityFromRequest(request, existingTask);
                    Task updatedTask = taskRepository.save(existingTask);
                    return taskMapper.toResponse(updatedTask);
                });
    }

    /**
     * Supprimer un task
     */
    public boolean delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Rechercher des tasks
     */
    @Transactional(readOnly = true)
    public Page<TaskResponse> search(String query, Pageable pageable) {
        // Implémentation basique - à adapter selon vos besoins
        Page<Task> results = taskRepository.findBySearchQuery(query, pageable);
        return results.map(taskMapper::toResponse);
    }

    /**
     * Vérifier si un task existe
     */
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return taskRepository.existsById(id);
    }

    /**
     * Compter le nombre total de tasks
     */
    @Transactional(readOnly = true)
    public long count() {
        return taskRepository.count();
    }

    @Transactional
    public Optional<TaskResponse> updateTaskStatus(UpdateTaskStatusRequest request,Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskMapper.UpdateTaskStatus(request, task);
                    Task saved = taskRepository.save(task);
                    return taskMapper.toResponse(saved);
                            });

    }
}