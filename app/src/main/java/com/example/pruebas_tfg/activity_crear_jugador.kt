package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.SpinnerEntrenadorAdapter
import com.example.pruebas_tfg.Model.Equipo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class activity_crear_jugador : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)
        val nombreJugador : EditText = findViewById(R.id.nombreJugador)
        val tarjetasAmarillas : EditText= findViewById(R.id.tarjetas_amarillas)
        val tarjetasRojas: EditText=findViewById(R.id.Tarjetas_Rojas)
        val Goles:EditText=findViewById(R.id.Goles)
        val asistencias:EditText=findViewById(R.id.asistencias)
        val spinner: Spinner=findViewById(R.id.spinnerEquipo)
        val btn:Button=findViewById(R.id.btnCrearJugador)
        val ListEquipos : Equipo





    }
}