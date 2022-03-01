package com.ibm.academia.fraudes.modelo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PaisDTO implements Serializable {
    @JsonProperty("countryName")
    private String nombre;

    @JsonProperty("countryCode3")
    private String codigoISO;

    @JsonProperty("currencies")
    private List<Map<String, Object>> monedas;

    @JsonProperty("rates")
    private List<String> cotizacionActual;
}
