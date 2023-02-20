package com.API.musica.controladores;

import com.API.musica.entidades.ListaMusica;
import com.API.musica.repositorios.RepositorioListaMusica;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
public class ControladorGuardar {

    RepositorioListaMusica repositorioListaMusica;

    public ControladorGuardar(RepositorioListaMusica repositorioListaMusica) {
        this.repositorioListaMusica = repositorioListaMusica;
    }

    @GetMapping("mostrar")
    @ResponseBody
    public List<ListaMusica> getDatos() {
        return this.repositorioListaMusica.findAll();
    }


    @PostMapping("mostrar")
    public ResponseEntity<ListaMusica> crear(@RequestBody ListaMusica nuevoRegistro) {
        try {
            this.repositorioListaMusica.save(nuevoRegistro);
            return ResponseEntity
                    .created(new URI("mostrar/" + nuevoRegistro.getHora()))
                    .body(nuevoRegistro);
        }
        catch (Exception ex) {
            return ResponseEntity.status(400)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }


}
