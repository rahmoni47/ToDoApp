package com.h80.demo.repository;

import com.h80.demo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour la gestion des Task
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Rechercher par task
     */
    List<Task> findByTaskContainingIgnoreCase(String task);
    Page<Task> findByTaskContainingIgnoreCase(String task, Pageable pageable);

    /**
     * Vérifier l'existence par critère
     */
    

    /**
     * Recherche personnalisée avec JPQL
     */
    @Query("SELECT e FROM Task e WHERE " +
           "LOWER(e.task) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Task> findBySearchQuery(@Param("query") String query, Pageable pageable);

    /**
     * Compter par critères
     */
    long countByTaskContainingIgnoreCase(String task);

    /**
     * Méthodes de tri personnalisées
     */
    List<Task> findAllByOrderByTaskAsc();
    List<Task> findAllByOrderByTaskDesc();
    List<Task> findAllByOrderByDateAsc();
    List<Task> findAllByOrderByDateDesc();
}