package com.API.musica.repositorios;

import com.API.musica.entidades.ListaMusica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RepositorioListaMusica extends JpaRepository<ListaMusica,String> {
}
