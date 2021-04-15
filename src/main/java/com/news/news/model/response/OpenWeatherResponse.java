package com.news.news.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
// Aki pondremos los campos q necesitaremos de la respuesta de nuestra peticion a una API
public class OpenWeatherResponse {

    @SerializedName("main")
    private Main main;

    @SerializedName("name")
    private String name;
}