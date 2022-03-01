package com.ibm.academia.fraudes.modelo.servicios;

import com.ibm.academia.fraudes.modelo.dto.PaisDTO;

public interface IpService {
    PaisDTO obtenerInfoIp(String ip);
}
