package com.API.musica.configuracion;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class OpenWeatherMapCache {

    private Map<String, Double> cacheResultados = new HashMap<>();

    @Scheduled(fixedRate = 180000) // 3 minutos en milisegundos
    public void limpiarCache() {
        cacheResultados.clear();
    }
}