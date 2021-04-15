package com.news.news.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "NoticiasVideo")

public class NoticiaVideo extends Noticia{

    private String tituloVideo;
    private String descripcionVideo;
    private String urlVideo;

    @Override
    public NoticiaEnum noticiasEnum() {
        return NoticiaEnum.VIDEO;
    }
}