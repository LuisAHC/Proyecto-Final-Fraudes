package com.ibm.academia.fraudes.modelo.repositorios;

import com.ibm.academia.fraudes.modelo.entidades.ListaNegra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("repositorioListaNegra")
public interface ListaNegraRepository extends CrudRepository<ListaNegra, Long> {
    @Query("select l from ListaNegra l where l.ip = ?1")
    Optional<ListaNegra> encontrarPorIp(String ip);
}
