package com.ibm.academia.fraudes.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Pais implements Serializable {
    @JsonProperty("countryName")
    private String nombre;

    @JsonProperty("countryCode3")
    private String codigoISO;

    @JsonProperty("currencies")
    private List<Map<String, Object>> monedas;

    @JsonProperty("rates")
    private List<String> cotizacionActual;


    public Pais(String nombre, String codigoISO, List<Map<String, Object>> monedas, List<String> cotizacionActual) {
        this.nombre = nombre;
        this.codigoISO = codigoISO;
        this.monedas = monedas;
        this.cotizacionActual = cotizacionActual;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pais{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", codigoISO='").append(codigoISO).append('\'');
        sb.append(", monedas='").append(monedas).append('\'');
        sb.append(", cotizacionActual=").append(cotizacionActual);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return nombre.equals(pais.nombre) && codigoISO.equals(pais.codigoISO) && monedas.equals(pais.monedas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, codigoISO, monedas);
    }
}
