package com.ibm.academia.fraudes.modelo.servicios;

import com.ibm.academia.fraudes.modelo.entidades.ListaNegra;

import java.util.Optional;

public interface ListaNegraService {
    Optional<ListaNegra> encontrarPorIp(String ip);
    void banearPorIp(String ip);
}
