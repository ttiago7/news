package com.news.news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity

public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NotBlank(message = "Nombre is mandatory")
    private String nombre;

    //@NotBlank(message = "Apellido is mandatory")
    private String apellido;

    //@NotNull
    //@JsonIgnore // en la respuesta del endpoint oculta esta propiedad para no enviarla en el json q dovolveremos //Equivalente al DTO
    private String dni;

}
