package com.news.news.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
// Aki pondremos los campos q necesitaremos de la respuesta de nuestra peticion a una API - esto se mostrara en el postman como nuestro response
public class ApiDolarResponse {

    @SerializedName("dolarBlue")
    private DolarBlue dolarBlue;

    @JsonProperty("dolarOficial")
    private DolarOficial dolarOficial;


    //con @SerializedName le podemos poner un nombre distinto en nuesto aojeto al q tiene la json response de la api consultada
//    @SerializedName("location")
//    private Location location;
//
//    @SerializedName("current")
//    private Current current;


    //con JsonProperty el nombre de la prop json debe ser igual al nombre de la prop de nuestra clase
//    @JsonProperty("location")//aki ponemos el nombre de la propiedad q nos devuelve el json en la peticion a la api, esto asigna este valor a la prop de abajo
//    private Location location;
//
//    @JsonProperty("current")//aki ponemos el nombre de la propiedad q nos devuelve el json en la peticion a la api
//    private Current current;
}