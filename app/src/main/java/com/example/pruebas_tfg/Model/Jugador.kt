package com.example.pruebas_tfg.Model

import java.io.Serializable
import java.time.LocalDate

 class Jugador(
    val id: Int,
    val nombre: String,
    val fechaNacimiento: LocalDate,
    val idEquipo: Int,
    val tarjetasAmarillas: Int,
    val tarjetasRojas: Int,
    val partidosJugados: Int,
    val goles: Int,
    val asistencias: Int,
    val imagen: String,
    val codPosicion: Int
) : Serializable {

 }