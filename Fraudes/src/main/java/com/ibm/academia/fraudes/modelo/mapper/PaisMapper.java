package com.ibm.academia.fraudes.modelo.mapper;

import com.ibm.academia.fraudes.modelo.dto.PaisDTO;
import com.ibm.academia.fraudes.modelo.entidades.Pais;

public class PaisMapper {
    public static PaisDTO mapPais(Pais pais){
        PaisDTO paisDto = new PaisDTO();
        paisDto.setNombre(pais.getNombre());
        paisDto.setCodigoISO(pais.getCodigoISO());
        paisDto.setMonedas(pais.getMonedas());
        paisDto.setCotizacionActual(pais.getCotizacionActual());
        return paisDto;
    }
}
