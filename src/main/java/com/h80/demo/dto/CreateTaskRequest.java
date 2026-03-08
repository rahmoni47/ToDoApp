package com.h80.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la création d'un Task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskRequest {

    @NotBlank(message = "Task ne peut pas être vide")
    @Size(max = 100, message = "Task ne peut pas dépasser 100 caractères")
    private String task;

    
    @NotBlank(message = "Task desctiption ne peut pas être vide")
    @Size(max = 1000, message = "Task desctiption ne peut pas dépasser 1000 caractères")
    private String description;
}