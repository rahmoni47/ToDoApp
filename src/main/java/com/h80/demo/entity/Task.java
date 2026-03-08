package com.h80.demo.entity;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The id of the task")
    private Long id;
    
    @Column(nullable = false,length = 100)
    @Schema(description = "the name of the task")
    private String task;

    @Column(nullable = false, length = 1000)
    @Schema(description = "the desctiption of the task")
    private String description;
    
    @Schema(description = "the time that the task created")
    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();
    
    @Column(nullable = false)
    private boolean done = false; 

}
