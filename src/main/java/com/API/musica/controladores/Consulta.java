package com.API.musica.controladores;

import com.API.musica.entidades.ListaMusica;
import com.API.musica.repositorios.RepositorioListaMusica;
import com.API.musica.servicios.Conector_OpenWeatherMap;
import com.API.musica.servicios.Conector_Spotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

import static com.API.musica.servicios.Conector_Spotify.genero;

@RestController
public class Consulta {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consulta.class);
    @Autowired
    Conector_OpenWeatherMap conector_openWeatherMap;

    @Autowired
    RepositorioListaMusica repositorioListaMusica;

    @Autowired
    Conector_Spotify conector_spotify;

    static double temperatura;
    static String canciones;

    @GetMapping("/api/temperatura")
    @ResponseBody
    public String getTemperatura(@RequestParam(value = "ciudad", required = false) String ciudad, @RequestParam(value = "latitud", required = false) Double latitud, @RequestParam(value = "longitud", required = false) Double longitud) {
        if (ciudad != null) {
            temperatura = conector_openWeatherMap.getURLCiudad(ciudad);
            canciones = conector_spotify.peticionGenero(temperatura);
            guardarMiEntidad(ciudad);

            LOGGER.debug("Temperatura en " + ciudad + ": " + temperatura + "     Celsius");
            return canciones;

        } else if (latitud != null && longitud != null) {
            temperatura = conector_openWeatherMap.getURLCordenada(latitud, longitud);
            canciones = conector_spotify.peticionGenero(temperatura);
            String ciudadConvertidad = latitud.toString() + " " + longitud.toString();
            guardarMiEntidad(ciudadConvertidad);

            LOGGER.debug("Temperatura: " + temperatura + "    Celsius");
            return canciones;
        } else {
            LOGGER.debug("Se requiere una ciudad o coordenadas de latitud y longitud");
            return "Se requiere una ciudad o coordenadas de latitud y longitud";
        }
    }

    public void guardarMiEntidad(String ciudad) {
        LocalTime horaActual = LocalTime.now();
        ListaMusica listaMusica = new ListaMusica();
        listaMusica.setHora(String.valueOf(horaActual));
        listaMusica.setGenero(genero);
        listaMusica.setCiudad(ciudad);
        listaMusica.setClima(String.valueOf(temperatura));
        listaMusica.setCanciones(canciones);
        repositorioListaMusica.save(listaMusica);
    }
}
