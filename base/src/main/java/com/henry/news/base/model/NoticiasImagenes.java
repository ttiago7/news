package com.henry.news.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity

public class NoticiasImagenes extends Noticias {

    private String urlImagenes; // no me deja list

    @Override
    public NoticiasEnum noticiasEnum() {
        return NoticiasEnum.IMAGENES;
    }
}

