package com.ibm.academia.fraudes.modelo.servicios;

import com.ibm.academia.fraudes.modelo.entidades.ListaNegra;
import com.ibm.academia.fraudes.modelo.repositorios.ListaNegraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("listaNegraServiceImpl")
public class ListaNegraServiceImpl implements ListaNegraService{

    protected final ListaNegraRepository repository;
    @Autowired
    public ListaNegraServiceImpl(ListaNegraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ListaNegra> encontrarPorIp(String ip) {
        return repository.encontrarPorIp(ip);
    }

    @Override
    public void banearPorIp(String ip) {
        ListaNegra listaNegra = new ListaNegra(null, ip);
        repository.save(listaNegra);
    }
}
