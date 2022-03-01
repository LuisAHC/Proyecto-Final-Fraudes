package com.ibm.academia.fraudes.controladores;

import com.ibm.academia.fraudes.modelo.entidades.ListaNegra;
import com.ibm.academia.fraudes.modelo.servicios.IpService;
import com.ibm.academia.fraudes.modelo.servicios.ListaNegraService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    @GetMapping("/buscar_info/{ip}")
    public ResponseEntity<?> buscarInfoIp(@PathVariable String ip){
        Optional<ListaNegra> estadoIp = listaNegraService.encontrarPorIp(ip);

        if (estadoIp.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            try {
                return new ResponseEntity<>(ipService.obtenerInfoIp(ip), HttpStatus.OK);
            }catch (FeignException fe){
                logger.info("mensaje: " + fe.getMessage() + " causa: " + fe.getCause());
                respuesta.put("mensaje", fe.getMessage());
                return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
            }
        }else
            return new ResponseEntity<>("La IP ingresada se encuentra en lista negra", HttpStatus.BAD_REQUEST);
    }
}
