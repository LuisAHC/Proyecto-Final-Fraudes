package com.ibm.academia.fraudes.clientes;

import com.ibm.academia.fraudes.modelo.entidades.Pais;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-restcountries", url = "https://restcountries.com/v2/alpha/")
public interface RestCountriesRest {
    @GetMapping("/{codigoISO}")
    Pais obtenerMonedaPais(@PathVariable String codigoISO) throws RuntimeException;
}
