package com.news.news.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class DolarOficial {
    @SerializedName("dolarHoy")
    private Dolar dolarHoy;

    @SerializedName("dolarSi")
    private Dolar dolarSi;
}
