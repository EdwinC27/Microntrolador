package com.API.musica.servicios;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import static com.API.musica.servicios.Conector_Spotify.*;

@Service
public class General_Json {

    public static JSONObject infoRegresada(String city, String temperatura, String cordenadas, String  nombrePlaylist) {
        JSONObject jsonObject = new JSONObject();

        // city
        JSONObject cityLista = new JSONObject();
        cityLista.put("name", city);
        cityLista.put("temperature", temperatura);
        cityLista.put("cordinates", cordenadas);
        cityLista.put("playlist", nombrePlaylist);

        JSONArray songsListas = new JSONArray();
        // songs
        for(int interador=0; interador<songNameArreglo.size(); interador++) {
            JSONObject songLista = new JSONObject();
            songLista.put("name", songNameArreglo.get(interador));
            songLista.put("artista", artistNameArreglo.get(interador));
            songLista.put("albun", albumNameArreglo.get(interador));

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