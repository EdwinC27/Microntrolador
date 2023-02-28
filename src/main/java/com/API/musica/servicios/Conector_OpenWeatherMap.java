package com.API.musica.servicios;

import com.API.musica.configuracion.OpenWeatherMapCache;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class Conector_OpenWeatherMap {

    @Value("${keyOpenWeatherMap}")
    private String apiKey;

    @Value("${urlOpenWeatherPeticion}")
    private String urlOpenWeatherPeticion;

    @Autowired
    OpenWeatherMapCache openWeatherMapCache;

    @PostConstruct
    public void iniciarLimpiarCache() {
        openWeatherMapCache.limpiarCache();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Conector_OpenWeatherMap.class);

    // Mapa para almacenar los resultados de las solicitudes
    private Map<String, String> cache = new HashMap<>();

    @Retryable(value = {ResourceAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public String getURLCiudad(String ciudad) {
        try {
            URL url = new URL(urlOpenWeatherPeticion + "q=" + ciudad + "&appid="+apiKey);

            return getTemperatura(url);
        } catch (Exception e) {
            LOGGER.debug("No se pudo traer la URL de OpenWeatherMaps con el nombre de la ciudad");
            return "No se pudo traer la URL de OpenWeatherMaps con el nombre de la ciudad";
        }
    }

    @Retryable(value = {ResourceAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public String getURLCordenada(double latitud, double longitud) {
        try {
            URL url = new URL(urlOpenWeatherPeticion + "lat=" + latitud + "&lon=" + longitud + "&appid="+apiKey);

            return getTemperatura(url);
        } catch (Exception e) {
            LOGGER.debug("No se pudo traer la URL de OpenWeatherMaps Con las cordenadas");
            return "No se pudo traer la URL de OpenWeatherMaps Con las cordenadas";
        }
    }

    private String getTemperatura(URL url) throws JSONException {
        String urlStr = url.toString();
        // Verificar si la solicitud ya está en caché
        if (cache.containsKey(urlStr)) {
            //LOGGER.debug("Obteniendo la temperatura de la caché para: " + urlStr);
            return cache.get(urlStr);
        }
        // Petición a la URL
        RestTemplate restTemplate = new RestTemplate();

        // getForObject es uno de los métodos de RestTemplate que permite hacer una solicitud y recibir una respuesta en formato JSON
        // Obtiene la respuesta de la petición
        String resultado = restTemplate.getForObject(url.toString(), String.class);

        // Crea el objeto JSON a partir de la variable anterior
        JSONObject jsonObject = new JSONObject(resultado);
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp"); // Te devuelve la temperatura en grados Kelvin

        // Almacenar el resultado en caché
        cache.put(urlStr, String.valueOf(temperature - 273.15));

        return String.valueOf(temperature - 273.15); // Convertir la temperatura en grados Celsius
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException ex) {
        LOGGER.error("Ocurrió un error en la aplicación: {}", ex.getMessage());
    }
}