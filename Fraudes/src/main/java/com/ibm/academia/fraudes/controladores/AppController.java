package com.ibm.academia.fraudes.controladores;

import com.ibm.academia.fraudes.modelo.dto.PaisDTO;
import com.ibm.academia.fraudes.modelo.entidades.ListaNegra;
import com.ibm.academia.fraudes.modelo.servicios.IpService;
import com.ibm.academia.fraudes.modelo.servicios.ListaNegraService;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cliente")
public class AppController {
    private final static Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    @Qualifier("ipServiceFeign")
    private IpService ipService;

    @Autowired
    @Qualifier("listaNegraServiceImpl")
    private ListaNegraService listaNegraService;

    /**
     * Endpoint para obtener información del país correspondiente a la IP ingresada
     * @param ip IP de la que se desea conocer información
     * @return Retorna un objeto con información del país
     * @author LAHC 27-02-2022
     */
    @CircuitBreaker(name = "info", fallbackMethod = "metodoAlternativo")
    @TimeLimiter(name = "info")
    @GetMapping("/buscar_info/{ip}")
    public CompletableFuture<ResponseEntity<?>> buscarInfoIp(@PathVariable String ip){
        Optional<ListaNegra> estadoIp = listaNegraService.encontrarPorIp(ip);

        if (estadoIp.isEmpty()){
            return CompletableFuture.supplyAsync((() -> new ResponseEntity<>(ipService.obtenerInfoIp(ip), HttpStatus.OK) ));
        }else
            return CompletableFuture.supplyAsync((() -> new ResponseEntity<>("La IP ingresada se encuentra en lista negra", HttpStatus.BAD_REQUEST)));
    }

    /**
     * Metodo alternativo para controlar problemas en buscarInfoIp()
     * @param e La excepción que causo el problema
     * @return Retorna una respuesta al error
     * @author LAHC 01-03-2022
     */
    public CompletableFuture<ResponseEntity<?>> metodoAlternativo(Throwable e) {
        logger.info("mensaje: " + e.getMessage() + " causa: " + e.getCause());
        Map<String, Object> respuesta = new HashMap<>();
        if (e instanceof FeignException){
            respuesta.put("mensaje", e.getMessage());
            throw new RuntimeException(respuesta.toString());
        }
        return CompletableFuture.supplyAsync((() -> new ResponseEntity<>("No se puede mostrar información, intentalo de nuevo", HttpStatus.BAD_REQUEST)));
    }
}
