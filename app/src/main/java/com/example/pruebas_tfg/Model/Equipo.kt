package com.example.pruebas_tfg.Model

import kotlinx.serialization.Serializable

@Serializable
data class Equipo(
    val idEquipo: Int,
    val nombreEquipo : String,
    val ciudad : String,
    val idEntrenador : Int,
    val partidosGanados : Int,
    val partidosPerdidos: Int,
    val partidosEmpatados : Int,
    val imagen : String
)
{
}