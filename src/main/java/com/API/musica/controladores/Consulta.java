package com.API.musica.controladores;

import com.API.musica.servicios.Conector_OpenWeatherMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consulta {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consulta.class);
    @Autowired
    Conector_OpenWeatherMap conector_openWeatherMap;

    @GetMapping("/")
    public void getTemperatura(@RequestParam(value = "ciudad", required = false) String ciudad,
                               @RequestParam(value = "latitud", required = false) Double latitud,
                               @RequestParam(value = "longitud", required = false) Double longitud) {
        if (ciudad != null) {
            double temperatura = conector_openWeatherMap.getURLCiudad(ciudad);

            LOGGER.debug("Temperatura en " + ciudad + ": " + temperatura + "     Celsius");

        } else if (latitud != null && longitud != null) {
            double temperatura = conector_openWeatherMap.getURLCordenada(latitud, longitud);

            LOGGER.debug("Temperatura: " + temperatura + "    Celsius");
        } else {
            LOGGER.debug("Se requiere una ciudad o coordenadas de latitud y longitud");
        }
    }
}
