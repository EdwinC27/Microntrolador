package com.API.musica.controladores;

import com.API.musica.configuracion.ComprobarConexionJava;
import com.API.musica.entidades.ListaMusica;
import com.API.musica.repositorios.RepositorioListaMusica;
import com.API.musica.servicios.Conector_OpenWeatherMap;
import com.API.musica.servicios.Conector_Spotify;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

import static com.API.musica.servicios.Conector_Spotify.genero;
import static com.API.musica.servicios.generalJson.erroGenerado;
import static com.API.musica.servicios.generalJson.infoRegresada;

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
    @Operation(summary = "Obtener la temperatura y las canciones recomendadas para una ciudad o coordenadas geográficas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperatura y canciones recomendadas obtenidas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos o faltantes"),
            @ApiResponse(responseCode = "500", description = "Error al conectarse con los servicios externos")
    })
    public JSONObject getTemperatura(@RequestParam(value = "ciudad", required = false) String ciudad, @RequestParam(value = "latitud", required = false) Double latitud, @RequestParam(value = "longitud", required = false) Double longitud) {
        ComprobarConexionJava comprobarConexionJava = new ComprobarConexionJava();

        if(comprobarConexionJava.conexion()) {
            try {
                if (ciudad != null) {
                    temperatura = conector_openWeatherMap.getURLCiudad(ciudad);
                    if(temperatura.equals("No se pudo traer la URL de OpenWeatherMaps con el nombre de la ciudad")) return erroGenerado("No se pudo traer la URL de OpenWeatherMaps con el nombre de la ciudad");

                    String resultadoCanciones = conector_spotify.peticionGenero(Double.parseDouble(temperatura));
                    if(resultadoCanciones.equals("Error al generar la lista de canciones")) return erroGenerado("Error al generar la lista de canciones");

                    canciones = String.join("#", resultadoCanciones);

                    guardarMiEntidad(ciudad);

                    LOGGER.debug("Temperatura en " + ciudad + ": " + temperatura + "     Celsius");
                    return infoRegresada(ciudad, temperatura, " " , genero);

                } else if (latitud != null && longitud != null) {
                    temperatura = conector_openWeatherMap.getURLCordenada(latitud, longitud);
                    if(temperatura.equals("No se pudo traer la URL de OpenWeatherMaps Con las cordenadas")) return erroGenerado("No se pudo traer la URL de OpenWeatherMaps Con las cordenadas");

                    String resultadoCanciones = conector_spotify.peticionGenero(Double.parseDouble(temperatura));
                    if(resultadoCanciones.equals("Error al generar la lista de canciones")) return erroGenerado("Error al generar la lista de canciones");
                    canciones = String.join("#", resultadoCanciones);

                    String ciudadConvertidad = latitud.toString() + " " + longitud.toString();
                    guardarMiEntidad(ciudadConvertidad);

                    LOGGER.debug("Temperatura: " + temperatura + "    Celsius");
                    return infoRegresada(" ", temperatura, ciudadConvertidad , genero);
                } else {
                    LOGGER.debug("Se requiere una ciudad o coordenadas de latitud y longitud");
                    return erroGenerado("Se requiere una ciudad o coordenadas de latitud y longitud");
                }
            } catch (Exception e) {
                return erroGenerado("Ocurrió un error inesperado: "+e.getMessage());
            }
        }
        else {
            return erroGenerado("Error al conectartes con los servidores");
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