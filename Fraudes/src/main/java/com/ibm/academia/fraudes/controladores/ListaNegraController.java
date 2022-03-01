package com.ibm.academia.fraudes.controladores;

import com.ibm.academia.fraudes.modelo.servicios.ListaNegraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
     * @author LAHC - 28-02-2022
     */
    @PutMapping("/banear/{ip}")
    public ResponseEntity<?> banearIP(@PathVariable String ip)	{
        Map<String, Object> respuesta = new HashMap<>();
        try {
            listaNegraService.banearPorIp(ip);
        } catch (Exception e) {
            logger.info("mensaje: " + e.getMessage() + " causa: " + e.getCause());
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("IP agregada a la lista negra correctamente", HttpStatus.OK);
    }
}
