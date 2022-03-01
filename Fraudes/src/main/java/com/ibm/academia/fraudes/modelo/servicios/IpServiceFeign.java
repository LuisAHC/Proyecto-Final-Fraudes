package com.ibm.academia.fraudes.modelo.servicios;

import com.ibm.academia.fraudes.clientes.CurrencyConverterRest;
import com.ibm.academia.fraudes.clientes.FixerRest;
import com.ibm.academia.fraudes.clientes.Ip2CountryRest;
import com.ibm.academia.fraudes.clientes.RestCountriesRest;
import com.ibm.academia.fraudes.modelo.dto.PaisDTO;
import com.ibm.academia.fraudes.modelo.entidades.Pais;
import com.ibm.academia.fraudes.modelo.mapper.PaisMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ipServiceFeign")
public class IpServiceFeign implements IpService{

    @Autowired
    private Ip2CountryRest ip2clientFeign;

    @Autowired
    private RestCountriesRest restCountriesFeign;

    @Autowired
    private CurrencyConverterRest currencyConverterFeign;

    @Autowired
    private FixerRest fixerRest;

    @Override
    public PaisDTO obtenerInfoIp(String ip) {
        Pais paisFinal = new Pais();
        try {
            Pais paisNombre = ip2clientFeign.obtenerInfoIp(ip);
            if (paisNombre.getNombre() != null){
                paisFinal.setNombre(paisNombre.getNombre());
                paisFinal.setCodigoISO(paisNombre.getCodigoISO());
                try {
                    Pais paisMonedas = restCountriesFeign.obtenerMonedaPais(paisNombre.getCodigoISO());
                    if (paisMonedas.getMonedas() != null){
                        paisFinal.setMonedas(paisMonedas.getMonedas());
                        try {
                            List<String> valores = new ArrayList<>();
                            for (Map<String, Object> moneda: paisMonedas.getMonedas()) {
                                valores.add(currencyConverterFeign.obtenerConversiones(moneda.get("code").toString()));
                            }
                            paisFinal.setCotizacionActual(valores);
                            return PaisMapper.mapPais(paisFinal);
                        }catch (FeignException fe){
                            System.out.println("CurrencyConverter no disponible, utilizando Fixer");
                            try {
                                List<String> valores = new ArrayList<>();
                                Map<String, Object> rates = (Map<String, Object>) fixerRest.obtenerConversiones().get("rates");
                                for (Map<String, Object> moneda: paisMonedas.getMonedas()) {
                                    if (rates.containsKey(moneda.get("code"))){
                                        valores.add("USD_"+moneda.get("code").toString() + ": " + rates.get(moneda.get("code")).toString());
                                    }
                                }
                                paisFinal.setCotizacionActual(valores);
                                return PaisMapper.mapPais(paisFinal);
                            }catch (FeignException fe2){
                                throw new RuntimeException("Fallaron Fixer y CurrencyConverter " + fe.getMessage());
                            }
                        }
                    }else
                        throw new RuntimeException("No pude encontrar monedas del país " + paisNombre.getNombre());
                }catch (FeignException fe){
                    throw new RuntimeException("Fallo Rest Countries " + fe.getMessage());
                }
            }else
                throw new RuntimeException("No pude encontrar a qué país pertenece la IP proporcionada");

        }catch (FeignException fe){
            throw new RuntimeException("Fallo Ip2Country" + fe.getMessage());
        }
    }
}
