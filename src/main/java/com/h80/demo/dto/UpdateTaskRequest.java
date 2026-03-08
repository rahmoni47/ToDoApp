package com.h80.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * DTO pour la mise à jour d'un Task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskRequest {

    @Size(max = 100, message = "Task ne peut pas dépasser 100 caractères")
    private String task;

    @Size(max = 1000, message = "Task desctiption ne peut pas dépasser 1000 caractères")
    private String description;
}