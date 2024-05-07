package com.example.pruebas_tfg

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Jugador

class activity_modificar_jugador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_jugador)
        val intent = intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador
        val spinner = findViewById<Spinner>(R.id.SpinnerEquipo)
        val btn = findViewById<Button>(R.id.btnmodificarJugador)
        val nombre = findViewById<EditText>(R.id.nombreJugador)
        val goles = findViewById<EditText>(R.id.goles)
        val asistencias = findViewById<EditText>(R.id.asistencias)
        val tarjetasAmarillas = findViewById<EditText>(R.id.tarjetasAmarillas)
        val tarjetasRojas=findViewById<EditText>(R.id.tarjetasRojas)

        if(jugador!=null){
            goles.setText(jugador.goles.toString())
            nombre.setText(jugador.nombre.toString())
            asistencias.setText(jugador.asistencias.toString())
            tarjetasAmarillas.setText(jugador.tarjetasAmarillas.toString())
            tarjetasRojas.setText(jugador.tarjetasRojas.toString())
        }

    }

}