package com.news.news.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "NoticiasImagenes")

public class NoticiaImagen extends Noticia{

    @ElementCollection
    private List<String> urlImagenes;

    @Override
    public NoticiaEnum noticiasEnum() {// cada uno de los hijos de Noticia devuelve el tipo de objeto q es
        return NoticiaEnum.IMAGENES;
    }
}
