package com.ibm.academia.fraudes.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "api-fixer", url = "http://data.fixer.io/api")
public interface FixerRest {
    @GetMapping("/latest?access_key=424dbcc19c5509fe4006ccb6610b5209")
    Map<String, Object> obtenerConversiones() throws RuntimeException;
}
