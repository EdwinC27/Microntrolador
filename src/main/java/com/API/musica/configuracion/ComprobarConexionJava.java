package com.API.musica.configuracion;

import java.net.Socket;

public class ComprobarConexionJava {
    String dirWeb = "www.google.com";
    int puerto = 80;

    public boolean conexion() {
        try {
            Socket s = new Socket(dirWeb, puerto);
            if (s.isConnected()) {
            return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
