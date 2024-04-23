package com.example.pruebas_tfg.BBDD

import android.provider.BaseColumns

class UsuarioContract {

    class UsuarioEntry : BaseColumns {
        companion object {
            var tableName = "Usuarios"
            var nombreUsuario = "NombreUsuario"
            var contraseña = "Contraseña"
            var imagen = "Imagen"
            var correo = "Correo"
        }
    }

companion object {
    private var SQL_CREATE_ENTRIES =
        "CREATE TABLE " + UsuarioEntry.tableName + " (" +
                BaseColumns._ID + "INTEGER PRIMARY KEY," +
                UsuarioEntry.nombreUsuario +" TEXT," +
                UsuarioEntry.contraseña+" TEXT," +
                UsuarioEntry.imagen + " TEXT," +
                UsuarioEntry.correo + " TEXT)"

    private var SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS "+ UsuarioEntry.tableName


}



}