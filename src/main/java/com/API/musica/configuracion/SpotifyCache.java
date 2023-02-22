package com.API.musica.configuracion;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class SpotifyCache {

    private Map<String, String> cacheResultados = new HashMap<>();

    private static final long MILISEGUNDO_HORA = 3_600_000;

    @Scheduled(fixedRate = MILISEGUNDO_HORA * 12) // 12 horas en milisegundos
    public void limpiarCache() {
        cacheResultados.clear();
    }
}