package com.henry.news.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity

public class NoticiasVideo extends Noticias {

    private String tituloVideo;
    private String descripcionVideo;
    private String urlVideo;

    @Override
    public NoticiasEnum noticiasEnum() {
        return NoticiasEnum.VIDEO;
    }
}
