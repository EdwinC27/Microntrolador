package com.API.musica.entidades;

import javax.persistence.*;

@Entity
@Table(name = "informacion_generada")
public class ListaMusica {

    public ListaMusica() {
    }

    public ListaMusica(String hora, String genero, String ciudad, String clima, String canciones) {
        this.hora = hora;
        this.genero = genero;
        this.ciudad = ciudad;
        this.clima = clima;
        this.canciones = canciones;
    }

    @Id
    private String hora;
    @Basic
    private String genero;
    @Basic
    private String ciudad;
    @Basic
    private String clima;

    @Basic
    private String canciones;


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getCanciones() {
        return canciones;
    }

    public void setCanciones(String canciones) {
        this.canciones = canciones;
    }
}