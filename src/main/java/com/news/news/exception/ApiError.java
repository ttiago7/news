package com.news.news.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    //los mensajes de error nos mostrara solo estos atributos
    private HttpStatus status;
    private String message;
    private List<String> errors;

}
