package com.example.pruebas_tfg.Model

import java.io.Serializable

class Liga(var codLiga: Int, var nombre: String, var nacional: Boolean): Serializable {
    override fun toString(): String {
        return nombre
    }
}
