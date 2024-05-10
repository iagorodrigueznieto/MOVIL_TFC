package com.example.pruebas_tfg.Model;


import java.io.Serializable;

public class Jugador implements Serializable {
    public Integer id_jugador;
    public String nombre;
    public String fechaNacimiento;
    public Integer id_equipo;
    public int tarjetasAmarillas;
    public int tarjetasRojas;
    public int partidosJugados;
    public int goles;
    public int asistencias;
    public String imagen;
    public int cod_Posicion;

    public Jugador(Integer id_jugador, String nombre, String fechaNacimiento, Integer id_equipo, int tarjetasAmarillas, int tarjetasRojas, int partidosJugados, int goles, int asistencias, String imagen, int Cod_Posicion) {
        this.id_jugador = id_jugador;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.id_equipo = id_equipo;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.partidosJugados = partidosJugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.imagen = imagen;
        this.cod_Posicion = Cod_Posicion;
    }

    public Jugador(String nombre, String fechaNacimiento, Integer id_equipo, int tarjetasAmarillas, int tarjetasRojas, int partidosJugados, int goles, int asistencias, String imagen, int Cod_Posicion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.id_equipo = id_equipo;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.partidosJugados = partidosJugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.imagen = imagen;
        this.cod_Posicion = Cod_Posicion;
    }
}
