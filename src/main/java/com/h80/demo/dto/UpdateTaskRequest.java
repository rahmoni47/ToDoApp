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

    @NotBlank(message = "Task should not Be empty")
    @Size(max=1000,message = "Task length shouldn't pass 1000 character")
    private String task;
}