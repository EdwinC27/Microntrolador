package com.API.musica.servicios;

import com.API.musica.configuracion.SpotifyCache;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
@EnableScheduling
public class Conector_Spotify {
    private static final Logger LOGGER = LoggerFactory.getLogger(Conector_Spotify.class);

    @Autowired
    private SpotifyCache spotifyCache;

    @Autowired
    private Llave_Spotify llave_spotify;

    private Map<String, String> cacheResultados = new HashMap<>();

    public String genero;
    public List<String> songNameArreglo;
    public List<String> artistNameArreglo;
    public List<String> albumNameArreglo;
    public List<String> songUlrArreglo;
    @Value("${urlSpotifyPeticion}")
    private String API_URL;

    @PostConstruct
    public void iniciarLimpiarCache() {
        spotifyCache.limpiarCache();
    }

    public String peticionCanciones(String accessToken, String genero) {
        String URL= API_URL + genero;
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
            return  mostrarInfo(mapper, response);
        } else {
            // Si los resultados no están en la caché, realiza una solicitud a la API de Spotify
            String response = restTemplate.exchange(URL, HttpMethod.GET, request, String.class, "<album_id>").getBody();
            cacheResultados.put(cacheKey, response); // Agrega los resultados a la caché
            ObjectMapper mapper = new ObjectMapper();
            return  mostrarInfo(mapper, response);
        }
    }

    public String mostrarInfo(ObjectMapper mapper, String response) {
        try {
            limpiarArreglosInfoGeneral();

            JsonNode root = mapper.readTree(response);
            JsonNode itemsNode = root.path("tracks").path("items");
            for (JsonNode item : itemsNode) {
                encontrarCanciones(item);

                encontrarAlbunes(item);

                encontrarUrls(item);

                JsonNode artistsNode = item.get("artists");
                encontrarArtistas(artistsNode);
            }
            return informacionAgrupada();
        } catch (IOException e) {
            return "Error al generar la lista de canciones";
        }
    }

    public void encontrarAlbunes(JsonNode item) {
        JsonNode albumNode = item.get("album");
        JsonNode albumNameNode = albumNode.get("name");
        albumNameArreglo.add(albumNameNode.asText());
    }

    public void encontrarUrls(JsonNode item) {
        JsonNode externalUrlsNode = item.get("external_urls");
        JsonNode spotifyUrlNode = externalUrlsNode.get("spotify");
        songUlrArreglo.add(spotifyUrlNode.asText());
    }

    public void encontrarCanciones(JsonNode item) {
        JsonNode nameNode = item.get("name");
        songNameArreglo.add(nameNode.asText());
    }

    public void encontrarArtistas(JsonNode artistsNode) {
        for (JsonNode artist : artistsNode) {
            JsonNode artistNameNode = artist.get("name");
            artistNameArreglo.add(artistNameNode.asText());
        }
    }

    public String informacionAgrupada(){
        StringBuilder sb = new StringBuilder();

        for(int interador=0; interador<albumNameArreglo.size(); interador++) {
            sb.append(artistNameArreglo.get(interador));
            sb.append(" - ");
            sb.append(albumNameArreglo.get(interador));
            sb.append(" - ");
            sb.append(songNameArreglo.get(interador));
            sb.append("     ---     ");
        }
        return sb.toString();
    }

    public String peticionGenero(double temperatura) {
        String token = llave_spotify.getToken();

        genero = "classical";

        if(temperatura > 30) genero = "party";
        else if (temperatura >= 15 && temperatura <=30) genero = "pop";
        else if (temperatura >= 10 && temperatura <= 14) genero = "rock";

        return peticionCanciones(token, genero);
    }

    public void limpiarArreglosInfoGeneral() {
        songNameArreglo = new ArrayList<>();
        artistNameArreglo = new ArrayList<>();
        albumNameArreglo = new ArrayList<>();
        songUlrArreglo = new ArrayList<>();
    }
}
