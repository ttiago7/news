package com.news.news.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity // indicamos q es una entidad
@Data //
@NoArgsConstructor // constructos sin argumentos, vacio

//JsonTypeInfo por la property noticiasEnum identifica los distintos tipos de objetos q eredan de noticia
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, visible = true, property = "noticiasEnum")
@JsonSubTypes({ // aqui van los tipos de hijos q existen, relaciona en name con la clase, de esta forma toma las propiedades del padre y le suma las props q le correspondan segun el hijo
        @JsonSubTypes.Type(value = NoticiaVideo.class, name = "VIDEO"), // value = objeto al q corresponde segun el name = identificador de cada clase
        @JsonSubTypes.Type(value = NoticiaImagen.class, name = "IMAGENES"),
        @JsonSubTypes.Type(value = NoticiaTexto.class, name = "TEXTO"),

})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // TABLE PER CLASS una tabla para cada entidad

public abstract class Noticia implements Serializable {//abstracta x q esta clase no se instanciara sino q las demas noticias heredaran d esta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "El campo titulo no puede estar vacio") // si pasa, lanza una RestResponseEntityExceptionHandler q atrapara la exception y la mostrara en formato ApiError
    private String titulo;
    private String descripcion;

    @AccessType(AccessType.Type.PROPERTY) //para q no genere este campo en la DB ya q no necesito guardar este dato
    public abstract NoticiaEnum noticiasEnum(); // con esta propiedad vamos a identificar los distintos json que nos van a llegar

    @ManyToOne(fetch = FetchType.EAGER) //todo el contenido, contrario LAZY
    @JoinColumn(name = "writer_id")
    private Writer owner;
}
