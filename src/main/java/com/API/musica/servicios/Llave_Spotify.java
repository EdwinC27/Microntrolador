package com.API.musica.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class Llave_Spotify {
    private static final Logger LOGGER = LoggerFactory.getLogger(Llave_Spotify.class);
    @Value("${AuthUrlSpotify}")
    private String AUTH_URL;
    @Value("${ClienteIdSpotify}")
    private String Client_ID;
    @Value("${ClienteSecretoSpotify}")
    private String Client_Secret;
    private String accessToken; // variable miembro para almacenar el token
    private Instant expirationTime; // variable miembro para almacenar la fecha de expiración del token


    public synchronized String getToken() { // método sincronizado para asegurar el acceso atómico a las variables miembro
        if (accessToken != null && expirationTime != null && Instant.now().isBefore(expirationTime)) {
            return accessToken; // si el token actual está vigente, devolverlo
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(Client_ID, Client_Secret);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String response = restTemplate.postForObject(AUTH_URL, request, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            accessToken = jsonNode.get("access_token").asText();
            expirationTime = Instant.now().plusSeconds(jsonNode.get("expires_in").asLong()); // establecer la fecha de expiración del token

            return accessToken;
        } catch (JsonProcessingException exception) {
            LOGGER.debug("Error al procesar el JSON de respuesta");
            throw new RuntimeException("Error al procesar el JSON de respuesta");
        }
    }
}
