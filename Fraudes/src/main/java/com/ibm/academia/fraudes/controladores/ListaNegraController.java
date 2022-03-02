package com.ibm.academia.fraudes.controladores;

import com.ibm.academia.fraudes.modelo.servicios.ListaNegraService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin")
public class ListaNegraController {
    private final static Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    @Qualifier("listaNegraServiceImpl")
    private ListaNegraService listaNegraService;

    /**
     * Endpoint para agregar una IP a la lista negra
     * @param ip IP que será agregada a la lista negra
     * @return Retorna mensaje diciendo si la IP fue correctamente agregada a la lista negra
     * @author LAHC 28-02-2022
     */
    @CircuitBreaker(name = "ban", fallbackMethod = "metodoAlternativo")
    @TimeLimiter(name = "ban")
    @PutMapping("/banear/{ip}")
    public CompletableFuture<ResponseEntity<?>> banearIP(@PathVariable String ip){
        Map<String, Object> respuesta = new HashMap<>();
        try {
            listaNegraService.banearPorIp(ip);
        } catch (Exception e) {
            logger.info("mensaje: " + e.getMessage() + " causa: " + e.getCause());
            return CompletableFuture.supplyAsync((() -> new ResponseEntity<>("La IP ya se encuentra en la lista negra", HttpStatus.BAD_REQUEST)));
        }
        return CompletableFuture.supplyAsync((() -> new ResponseEntity<>("IP agregada a la lista negra correctamente", HttpStatus.OK)));
    }

    public CompletableFuture<ResponseEntity<?>> metodoAlternativo(Throwable e) {
        Map<String, Object> respuesta = new HashMap<>();
        logger.info("mensaje: " + e.getMessage() + " causa: " + e.getCause());
        respuesta.put("mensaje", e.getMessage());
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>("No se pudo banear la IP, intentalo de nuevo", HttpStatus.BAD_REQUEST));
    }
}
