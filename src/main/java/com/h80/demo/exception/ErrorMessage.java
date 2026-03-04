package com.h80.demo.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ErrorMessage {

    private String error;
    private String message; 
}
