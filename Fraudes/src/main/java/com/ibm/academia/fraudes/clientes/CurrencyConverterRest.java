package com.ibm.academia.fraudes.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-currencyconverter", url = "https://free.currconv.com/api/v7")
public interface CurrencyConverterRest {
        @GetMapping("/convert?apiKey=1c90967d9e0ae6ff4e4e&compact=ultra&q=EUR_{moneda}")
        String obtenerConversiones(@PathVariable String moneda) throws RuntimeException;
}
