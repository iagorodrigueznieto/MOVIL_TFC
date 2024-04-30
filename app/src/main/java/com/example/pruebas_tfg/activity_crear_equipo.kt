package com.example.pruebas_tfg

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class activity_crear_equipo : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)
        val button : Button = findViewById(R.id.btnCrear)
        val spinner : Spinner = findViewById(R.id.eleccionEntrenador)
        val ciudad : EditText = findViewById(R.id.editCiudad)
        val nombreEquipo : EditText = findViewById(R.id.nombreEquipo)


    }

}