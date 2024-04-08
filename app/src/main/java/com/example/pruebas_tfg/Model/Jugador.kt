package com.example.pruebas_tfg.Model

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Jugador(
    val id: Int,
    val nombre : String,
    val apellidos : String,
    val fechaNacimiento : LocalDate,
    val idEquipo: Equipo,
    val tarjetasAmarillas : Int,
    val tarjetasRojas : Int,
    val partidosJugados : Int,
    val goles : Int,
    val asistencias : Int,
    val Imagen : String
) {


}