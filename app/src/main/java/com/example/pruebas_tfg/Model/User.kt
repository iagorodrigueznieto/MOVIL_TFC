package com.example.pruebas_tfg.Model

import java.io.Serializable

 class User(
    val login: String,
    val correo: String,
    val contraseña: String,
    val cod_Rol: Int,
     val imagen: String
) : Serializable
