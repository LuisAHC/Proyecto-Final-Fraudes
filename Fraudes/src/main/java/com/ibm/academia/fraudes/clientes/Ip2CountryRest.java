package com.ibm.academia.fraudes.clientes;

import com.ibm.academia.fraudes.modelo.entidades.Pais;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-ip2country", url = "https://api.ip2country.info")
public interface Ip2CountryRest {
    @GetMapping("/ip?{ip}")
    Pais obtenerInfoIp(@PathVariable String ip) throws RuntimeException;
}
