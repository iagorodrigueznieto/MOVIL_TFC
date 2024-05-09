package com.example.pruebas_tfg.Model;


import java.io.Serializable;

public class Jugador implements Serializable {
    public Integer idJugador;
    public String nombre;
    public String fechaNacimiento;
    public Integer idEquipo;
    public int tarjetasAmarillas;
    public int tarjetasRojas;
    public int partidosJugados;
    public int goles;
    public int asistencias;
    public String imagen;
    public int codPosicion;

    public Jugador(Integer idJugador, String nombre, String fechaNacimiento, Integer idEquipo, int tarjetasAmarillas, int tarjetasRojas, int partidosJugados, int goles, int asistencias, String imagen, int codPosicion) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.idEquipo = idEquipo;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.partidosJugados = partidosJugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.imagen = imagen;
        this.codPosicion = codPosicion;
    }

    public Jugador(String nombre, String fechaNacimiento, Integer idEquipo, int tarjetasAmarillas, int tarjetasRojas, int partidosJugados, int goles, int asistencias, String imagen, int codPosicion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.idEquipo = idEquipo;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.partidosJugados = partidosJugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.imagen = imagen;
        this.codPosicion = codPosicion;
    }
}
