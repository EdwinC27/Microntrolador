package com.API.musica.servicios;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class General_Json {
    @Autowired
    Conector_Spotify conector_spotify;

    public JSONObject infoRegresada(String city, String temperatura, String cordenadas, String  nombrePlaylist) {
        JSONObject jsonObject = new JSONObject();

        // city
        JSONObject cityLista = new JSONObject();
        cityLista.put("name", city);
        cityLista.put("temperature", temperatura);
        cityLista.put("cordinates", cordenadas);
        cityLista.put("playlist", nombrePlaylist);

        JSONArray songsListas = new JSONArray();
        // songs
        for(int interador=0; interador<conector_spotify.songNameArreglo.size(); interador++) {
            JSONObject songLista = new JSONObject();
            songLista.put("name", conector_spotify.songNameArreglo.get(interador));
            songLista.put("artista", conector_spotify.artistNameArreglo.get(interador));
            songLista.put("albun", conector_spotify.albumNameArreglo.get(interador));
            songLista.put("url", conector_spotify.songUlrArreglo.get(interador));

            songsListas.add(songLista);
        }

        jsonObject.put("city", cityLista);
        jsonObject.put("songs", songsListas);

        return jsonObject;
    }


    public static JSONObject erroGenerado(String error) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("error", error);

        return jsonObject;
    }
}