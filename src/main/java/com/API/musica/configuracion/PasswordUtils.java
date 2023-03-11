package com.API.musica.configuracion;

import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class PasswordUtils {

    public static boolean verifyPassword(String passwordJson, String passwordBaseDatos) {
        String passwordJsonHash = hashPasswordJson(passwordJson);
        return passwordJsonHash.equals(passwordBaseDatos);
    }

    public static String hashPasswordJson(String passwordJson) {
        String passwordJsonHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // utiliza el algoritmo SHA-256
            byte[] hashInBytes = md.digest(passwordJson.getBytes(StandardCharsets.UTF_8)); // generar una secuencia de bytes hash a partir de la cadena
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) { // convierte en una cadena hexadecimal
                sb.append(String.format("%02x", b));
            }
            passwordJsonHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passwordJsonHash;
    }
}
