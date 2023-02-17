package com.API.musica.servicios;

import com.API.musica.configuracion.SpotifyCache;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableScheduling
public class Conector_Spotify {
    private static final Logger LOGGER = LoggerFactory.getLogger(Conector_Spotify.class);

    @Autowired
    private SpotifyCache spotifyCache;

    @Autowired
    private Llave_Spotify llave_spotify;

    private Map<String, String> cacheResultados = new HashMap<>();

    @PostConstruct
    public void iniciarLimpiarCache() {
        spotifyCache.limpiarCache();
    }

    public void peticionCanciones(String accessToken, String genero) {
        String API_URL = "https://api.spotify.com/v1/search?q=genre:" + genero + "&type=track";
        String cabezeraConToken = "Bearer " + accessToken;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", cabezeraConToken);
        HttpEntity<String> request = new HttpEntity<>(headers);

        String cacheKey = genero + "_" + accessToken;

        // Busca en la caché de resultados primero
        if (cacheResultados.containsKey(cacheKey)) {
            String response = cacheResultados.get(cacheKey);
            ObjectMapper mapper = new ObjectMapper();

            mostrarCanciones(mapper, response);
        } else {
            // Si los resultados no están en la caché, realiza una solicitud a la API de Spotify
            String response = restTemplate.exchange(API_URL, HttpMethod.GET, request, String.class, "<album_id>").getBody();
            cacheResultados.put(cacheKey, response); // Agrega los resultados a la caché
            ObjectMapper mapper = new ObjectMapper();

            mostrarCanciones(mapper, response);
        }
    }

    public void mostrarCanciones(ObjectMapper mapper, String response) {
        try {
            JsonNode root = mapper.readTree(response);
            JsonNode itemsNode = root.path("tracks").path("items");
            for (JsonNode item : itemsNode) {
                JsonNode nameNode = item.path("name");
                String name = nameNode.asText();
                LOGGER.debug(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void peticionGenero(double temperatura) {
        String token = llave_spotify.getToken();

        if(token.equals(" ")) {
            LOGGER.debug("Error en el token");
            return;
        }

        String genero = "clasica";

        if(temperatura > 30) genero = "fiesta";
        else if (temperatura >= 15 && temperatura <=30) genero = "pop";
        else if (temperatura >= 10 && temperatura <= 14) genero = "rock";

        peticionCanciones(token, genero);
    }
}
