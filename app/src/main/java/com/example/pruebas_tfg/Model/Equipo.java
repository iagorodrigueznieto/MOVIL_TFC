package com.example.pruebas_tfg.Model;

import kotlinx.serialization.Serializable;

@Serializable
public class Equipo {
    private Integer id;
    private String nombreEquipo;

    private String ciudad;

    private Integer idEntrenador;

    private String imagen;

    public Equipo(String nombre, String ciudad, Integer idEntrenador, String imagen) {
        this.nombreEquipo = nombre;
        this.ciudad = ciudad;
        this.idEntrenador = idEntrenador;
        this.imagen = imagen;
    }

    public Equipo(Integer id, String nombre, String ciudad, Integer idEntrenador, String imagen) {
        this.id = id;
        this.nombreEquipo = nombre;
        this.ciudad = ciudad;
        this.idEntrenador = idEntrenador;
        this.imagen = imagen;
    }
}
