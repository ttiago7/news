package com.news.news.model;

public enum NoticiaEnum {
    //definimos los posibles valores para enum
    VIDEO("Video Noticia"),
    IMAGENES("Imagenes Noticia"),
    TEXTO("Texto Noticia");

    private String descripcion;

    NoticiaEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static NoticiaEnum find(String value){
        for( NoticiaEnum v: values()){ //values es una property de enum q m devuelve todos los valores q tiene
            if(v.toString().equalsIgnoreCase(value)){//equalsIgnoreCase ignora mayusculas y minusculas
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid NoticiasType: %s", value));// si pasamos un valor q no existe en el enum
    }
}
