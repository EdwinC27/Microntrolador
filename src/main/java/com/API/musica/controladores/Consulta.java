package com.API.musica.controladores;

import com.API.musica.configuracion.ComprobarConexionJava;
import com.API.musica.entidades.ListaMusica;
import com.API.musica.repositorios.RepositorioListaMusica;
import com.API.musica.servicios.Conector_OpenWeatherMap;
import com.API.musica.servicios.Conector_Spotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

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

    static String temperatura;
    static String canciones;

    @GetMapping("/api/temperatura")
    public List<String> getTemperatura(@RequestParam(value = "ciudad", required = false) String ciudad, @RequestParam(value = "latitud", required = false) Double latitud, @RequestParam(value = "longitud", required = false) Double longitud) {
        ComprobarConexionJava comprobarConexionJava = new ComprobarConexionJava();

        if(comprobarConexionJava.conexion()) {
            try {
                if (ciudad != null) {
                    temperatura = conector_openWeatherMap.getURLCiudad(ciudad);

                    canciones = String.join("#", conector_spotify.peticionGenero(Double.parseDouble(temperatura)));
                    guardarMiEntidad(ciudad);

                    LOGGER.debug("Temperatura en " + ciudad + ": " + temperatura + "     Celsius");
                    return conector_spotify.peticionGenero(Double.parseDouble(temperatura));

                } else if (latitud != null && longitud != null) {
                    temperatura = conector_openWeatherMap.getURLCordenada(latitud, longitud);
                    if(temperatura.equals("Ocurrió un error inesperado al obtener la temperatura")) return List.of("Ocurrió un error inesperado al obtener la temperatura");
                    if(temperatura.equals("Ocurrió un error inesperado al obtener la temperatura en la ciudad")) return List.of("Ocurrió un error inesperado al obtener la temperatura en la ciudad");

                    canciones = String.join("#", conector_spotify.peticionGenero(Double.parseDouble(temperatura)));
                    String ciudadConvertidad = latitud.toString() + " " + longitud.toString();
                    guardarMiEntidad(ciudadConvertidad);

                    LOGGER.debug("Temperatura: " + temperatura + "    Celsius");
                    return conector_spotify.peticionGenero(Double.parseDouble(temperatura));
                } else {
                    LOGGER.debug("Se requiere una ciudad o coordenadas de latitud y longitud");
                    return List.of("Se requiere una ciudad o coordenadas de latitud y longitud");
                }
            } catch (Exception e) {
                if(temperatura.equals("Ocurrió un error inesperado al obtener la temperatura")) return List.of("Ocurrió un error inesperado al obtener la temperatura");
                if(temperatura.equals("Ocurrió un error inesperado al obtener la temperatura en la ciudad")) return List.of("Ocurrió un error inesperado al obtener la temperatura en la ciudad");
            }
            return List.of("Error al conectartes");
        }
        else {
            return List.of("Error al conectartes");
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
