package com.news.news.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//nuestro objeto DTO
@Data
@NoArgsConstructor
public class WriterDTO {

    //no nececito las notations de validaciones
    private Integer id;
    private String nombre;
    private String apellido;
    //este objeto mostraremos, sin DNI

    //liego usamos el converter, mapea campo a campo
}