package com.news.news.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "NoticiasTexto")

public class NoticiaTexto extends Noticia{

    private String texto;

    @Override
    public NoticiaEnum noticiasEnum() {
        return NoticiaEnum.TEXTO;
    }
}