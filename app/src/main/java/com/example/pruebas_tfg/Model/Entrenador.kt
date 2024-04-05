package com.example.pruebas_tfg.Model

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Entrenador(
    val id : Int,
    val nombre : String,
    val apellido : String,
    val nacionalidad : String,
    val fechaDeNacimiento : LocalDate
) {
}