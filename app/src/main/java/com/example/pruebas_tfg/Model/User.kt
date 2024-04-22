package com.example.pruebas_tfg.Model

import java.io.Serializable

 class User(
    val id_usuario: Int,
    val login: String,
    val correo: String,
    val contraseña: String,
    val cod_rol: Int
) : Serializable
