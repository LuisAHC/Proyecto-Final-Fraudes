package com.ibm.academia.fraudes.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "api-fixer", url = "http://data.fixer.io/api")
public interface FixerRest {
    @GetMapping("/latest?access_key=50213c59d76beb36468ba6a574ea54bf")
    Map<String, Object> obtenerConversiones() throws RuntimeException;
}
